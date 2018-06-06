package org.udistrital.ojs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.udistrital.ojs.daos.UsuarioDao;
import org.udistrital.ojs.entities.Usuario;

@Service("UsuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public List<Usuario> listar() {
		return usuarioDao.listar();
	}

}
