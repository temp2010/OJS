package org.udistrital.ojs.services;

import java.util.List;

import org.udistrital.ojs.entities.Estado;
import org.udistrital.ojs.entities.Usuario;
import org.udistrital.ojs.entities.UsuarioRegistrado;

public interface UsuarioService {
	
	public List<Usuario> listar();
	
	public List<Usuario> listar(Estado estado);
	
	public Usuario buscar(String id);

	public void crear(Usuario usuario);
	
	public void crear(UsuarioRegistrado usuario);
	
}
