package org.udistrital.ojs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.udistrital.ojs.daos.SoporteDao;
import org.udistrital.ojs.models.Soporte;

@Service("SoporteService")
@Transactional
public class SoporteServiceImpl implements SoporteService {
	
	@Autowired
	SoporteDao soporteDao;

	@Override
	public void crear(Soporte soporte) {
		soporteDao.crear(soporte);
	}

}
