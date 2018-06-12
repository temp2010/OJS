package org.udistrital.ojs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.udistrital.ojs.models.Rol;
import org.udistrital.ojs.services.RolService;

@Controller
public class RolController {

	@Autowired
	private RolService rolService;

	@RequestMapping(value = "/pages/roles", method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView model = new ModelAndView();

		List<Rol> roles = rolService.listar();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("rol", auth.getAuthorities());
		model.addObject("roles", roles);
		
		return model;
	}
}
