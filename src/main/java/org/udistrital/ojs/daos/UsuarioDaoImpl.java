package org.udistrital.ojs.daos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.udistrital.ojs.models.Estado;
import org.udistrital.ojs.models.Reporte;
import org.udistrital.ojs.models.Usuario;
import org.udistrital.ojs.models.UsuarioRegistrado;

@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Usuario> listar() {
		return (List<Usuario>) sessionFactory.getCurrentSession().createQuery("FROM Usuario").list();
	}

	@Override
	public List<Usuario> listar(Estado estado) {
		List<String> uId = new ArrayList<>();
		List<UsuarioRegistrado> usuarioRegistrado = sessionFactory.getCurrentSession()
				.createQuery("FROM UsuarioRegistrado WHERE idEstado = ?)").setParameter(0, estado.getId()).list();
		for (UsuarioRegistrado usuario : usuarioRegistrado) {
			uId.add(String.valueOf(usuario.getUsuario()));
		}
		if (!uId.isEmpty()) {
			return (List<Usuario>) sessionFactory.getCurrentSession()
					.createQuery("FROM Usuario WHERE idUsuario IN (" + String.join(",", uId) + ") AND uEstado = 0")
					.list();
		}

		return Collections.emptyList();
	}

	@Override
	public void crear(Usuario usuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(usuario);
	}

	@Override
	public void crear(UsuarioRegistrado usuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(usuario);
	}

	@Override
	public Usuario buscar(String id) {
		return (Usuario) sessionFactory.getCurrentSession().createQuery(
				"FROM Usuario WHERE CONVERT(VARCHAR(32), HashBytes('MD5', CONVERT(varchar(10), idUsuario)), 2) = ?")
				.setParameter(0, id).list().get(0);
	}

	@Override
	public Usuario validar(String correo) {
		List<Usuario> usuario = sessionFactory.getCurrentSession().createQuery("FROM Usuario WHERE Correo = ?")
				.setParameter(0, correo).list();

		if (!usuario.isEmpty()) {
			return usuario.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<List<Map<Object, Object>>> datos() {

		List<Reporte> reporte = sessionFactory.getCurrentSession().createQuery("FROM Reporte").list();

		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<>();
		List<Map<Object, Object>> dataPoints2 = new ArrayList<>();


		for(Reporte columna : reporte) {
			map = new HashMap<>();
			map.put("x", columna.getEstado());
			map.put("y", columna.getCantidad());
			if(columna.getRol().equals("ESCRITOR")) {
				dataPoints1.add(map);
			} else {
				dataPoints2.add(map);
			}
		}

		list.add(dataPoints1);
		list.add(dataPoints2);

		return list;
	}

}
