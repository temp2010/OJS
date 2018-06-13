package org.udistrital.ojs.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.udistrital.ojs.daos.UsuarioDao;
import org.udistrital.ojs.models.Estado;
import org.udistrital.ojs.models.Usuario;
import org.udistrital.ojs.models.UsuarioRegistrado;

@Service("UsuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public List<Usuario> listar() {
		return usuarioDao.listar();
	}
	
	@Override
	public List<Usuario> listar(Estado estado) {
		return usuarioDao.listar(estado);
	}
	
	@Override
	public Usuario buscar(String id) {
		return usuarioDao.buscar(id);
	}
	
	@Override
	public Usuario validar(String correo) {
		return usuarioDao.validar(correo);
	}

	@Override
	public void crear(Usuario usuario) {
		usuarioDao.crear(usuario);
	}

	@Override
	public void crear(UsuarioRegistrado usuario) {
		usuarioDao.crear(usuario);
	}

	@Override
	public List<List<Map<Object, Object>>> datos(String desde, String hasta) {
		return usuarioDao.datos(desde, hasta);
	}

}
