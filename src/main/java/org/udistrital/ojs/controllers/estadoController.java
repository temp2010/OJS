package org.udistrital.ojs.controllers;

import org.udistrital.ojs.entities.Estado;
import org.udistrital.ojs.entities.UsuarioRegistrado;

public class estadoController {
	
	public estadoController() {
	}
	
	public Estado obtenerEstado(UsuarioRegistrado usuarioRegistrado) {
		
		Estado estado = null;
		
		if(usuarioRegistrado == null) {
			estado = new Estado(1, "Registrado", 1);
		}
		
		return estado;
	}

}
