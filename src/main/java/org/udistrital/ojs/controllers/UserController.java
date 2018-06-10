package org.udistrital.ojs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.udistrital.ojs.entities.Estado;
import org.udistrital.ojs.entities.Usuario;
import org.udistrital.ojs.services.UsuarioService;

@Controller
public class UserController {
	
	@Autowired
	private UsuarioService usuarioService;
	
    @RequestMapping(value="/pages/users", method=RequestMethod.GET)
    public ModelAndView listar(@RequestParam("accion") String accion) {
    	ModelAndView model = new ModelAndView();
    	List<Usuario> usuarios;
    	
    	if(accion.equals("validar")) {
    		usuarios = usuarioService.listar(new Estado(1, "Registrado", 1));
    	} else if (accion.equals("comite")) {
    		usuarios = usuarioService.listar(new Estado(4, "Aprobado Evaluador", 4));
    	} else {
    		usuarios = usuarioService.listar();
    	}
    	
    	model.addObject ("usuarios", usuarios);
        return model;
    }

}
