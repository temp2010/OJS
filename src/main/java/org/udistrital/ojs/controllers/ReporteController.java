package org.udistrital.ojs.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.udistrital.ojs.services.UsuarioService;

@Controller
public class ReporteController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/pages/reporte", method=RequestMethod.GET)
    public ModelMap mostrar() {
		
		ModelMap model = new ModelMap();
		
		List<List<Map<Object, Object>>> datosReporte = usuarioService.datos();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("rol", auth.getAuthorities());
		model.addAttribute("datosReporte", datosReporte);
		
		return model;
	}
}
