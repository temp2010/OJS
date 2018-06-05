package org.udistrital.ojs.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.udistrital.ojs.entities.Area;

@Repository("areaDao")
public class AreaDaoImpl implements AreaDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Area> listar() {
		return (List<Area>) sessionFactory.getCurrentSession().createQuery("FROM Area").list();
	}

}