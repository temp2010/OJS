package org.udistrital.ojs.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	public void crear(Usuario usuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(usuario);
	}

	@Override
	public void crear(UsuarioRegistrado usuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(usuario);
	}

}
