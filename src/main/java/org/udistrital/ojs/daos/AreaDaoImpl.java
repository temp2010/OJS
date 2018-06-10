package org.udistrital.ojs.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.udistrital.ojs.models.Area;

@Repository("areaDao")
public class AreaDaoImpl implements AreaDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Area> listar() {
		return (List<Area>) sessionFactory.getCurrentSession().createQuery("FROM Area").list();
	}

	@Override
	public Area buscar(Integer id) {
		return (Area) sessionFactory.getCurrentSession().createQuery("FROM Area WHERE idArea = ?").setParameter(0, id).list().get(0);
	}

}
