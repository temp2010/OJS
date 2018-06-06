package org.udistrital.ojs.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.udistrital.ojs.entities.Rol;

@Repository("rolDao")
public class RolDaoImpl implements RolDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Rol> listar() {
		return (List<Rol>) sessionFactory.getCurrentSession().createQuery("FROM Rol").list();
	}

}
