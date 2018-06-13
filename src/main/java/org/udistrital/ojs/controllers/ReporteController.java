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
import org.springframework.web.bind.annotation.RequestParam;
import org.udistrital.ojs.services.UsuarioService;

@Controller
public class ReporteController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/pages/reporte", method=RequestMethod.GET)
    public ModelMap mostrar(@RequestParam Map<String, String> request) {
		
		ModelMap model = new ModelMap();
		
		String desde = request.get("form-desde");
		String hasta = request.get("form-hasta");
		if(desde == null) {
			desde = "2018-05-12";
			hasta = "2018-06-12";
		}
		
		List<List<Map<Object, Object>>> datosReporte = usuarioService.datos(desde, hasta);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("rol", auth.getAuthorities());
		model.addAttribute("datosReporte", datosReporte);
		model.addAttribute("desde", desde);
		model.addAttribute("hasta", hasta);
		
		return model;
	}
}
