package org.udistrital.ojs.daos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.udistrital.ojs.entities.Estado;
import org.udistrital.ojs.entities.Usuario;
import org.udistrital.ojs.entities.UsuarioRegistrado;

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
					.createQuery("FROM Usuario WHERE idUsuario IN (" + String.join(",", uId) + ")").list();
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

}
