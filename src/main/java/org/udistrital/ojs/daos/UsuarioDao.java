package org.udistrital.ojs.daos;

import java.util.List;
import java.util.Map;

import org.udistrital.ojs.models.Estado;
import org.udistrital.ojs.models.Usuario;
import org.udistrital.ojs.models.UsuarioRegistrado;

public interface UsuarioDao {
	
	public List<Usuario> listar();
	
	public List<Usuario> listar(Estado estado);
	
	public Usuario buscar(String id);
	
	public Usuario validar(String id);

	public void crear(Usuario usuario);
	
	public void crear(UsuarioRegistrado usuario);
	
	public List<List<Map<Object, Object>>> datos(String desde, String hasta);
	
}
