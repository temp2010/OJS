package org.udistrital.ojs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.udistrital.ojs.entities.Usuario;
import org.udistrital.ojs.services.UsuarioService;

@Controller
public class UserController {
	
	@Autowired
	private UsuarioService usuarioService;
	
    @RequestMapping(value="/pages/users", method=RequestMethod.GET)
    public ModelAndView listar() {
    	ModelAndView model = new ModelAndView();
    	List<Usuario> usuarios = usuarioService.listar();
    	model.addObject ("usuarios", usuarios);
        return model;
    }

}
