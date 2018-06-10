package org.udistrital.ojs.daos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.udistrital.ojs.entities.Estado;

@Repository("estadoDao")
public class EstadoDaoImpl implements EstadoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Estado buscar(Integer orden) {
		return (Estado) sessionFactory.getCurrentSession().createQuery("FROM Estado WHERE Orden = ?")
				.setParameter(0, orden).list().get(0);
	}

}
