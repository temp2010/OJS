package org.udistrital.ojs.daos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.udistrital.ojs.entities.Soporte;

@Repository("soporteDao")
public class SoporteDaoImpl implements SoporteDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crear(Soporte soporte) {
		sessionFactory.getCurrentSession().saveOrUpdate(soporte);
	}

}
