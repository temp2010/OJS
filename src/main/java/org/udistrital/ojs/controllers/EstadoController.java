package org.udistrital.ojs.controllers;

import org.springframework.stereotype.Controller;
import org.udistrital.ojs.entities.Estado;
import org.udistrital.ojs.entities.UsuarioRegistrado;

@Controller
public class EstadoController {
	
	public Estado obtenerEstado(UsuarioRegistrado usuarioRegistrado, String accion) {
		
		Estado estado = new Estado();
		
		switch(accion) {
			case "aceptar":
				estado.setId(usuarioRegistrado.getEstado().getId() + 1);
				break;
				
			case "devolver":
				estado.setId(1);
				break;
				
			case "rechazar":
				estado.setId(5);
				break;
				
			default:
				estado.setId(2);
				break;
		}
		
		return estado;
	}

}
