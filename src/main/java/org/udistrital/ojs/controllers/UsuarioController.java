package org.udistrital.ojs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.udistrital.ojs.models.Estado;
import org.udistrital.ojs.models.Soporte;
import org.udistrital.ojs.models.Usuario;
import org.udistrital.ojs.services.EstadoService;
import org.udistrital.ojs.services.UsuarioService;

@Controller
public class UsuarioController {
	
	private String accionGlobal;
	private EstadoController eC = new EstadoController();

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EstadoService estadoService;

	@RequestMapping(value = "/pages/users", method = RequestMethod.GET)
	public ModelAndView listar(@RequestParam("accion") String accion) {

		accionGlobal = accion;
		ModelAndView model = new ModelAndView();
		
		model.addObject("usuarios", listaUsuarios());

		return model;
	}
	
	@RequestMapping(value = "/pages/users", method = RequestMethod.POST)
	public ModelAndView guardar(@RequestParam Map<String, String> request) {
		
		ModelAndView model = new ModelAndView();
		Usuario usuarioAprobar = usuarioService.buscar(request.get("form-user"));
		
		Estado estado = eC.obtenerEstado(usuarioAprobar.getUsuarioRegistrado(), request.get("form-action"));
		usuarioAprobar.getUsuarioRegistrado().setEstado(estado);
		usuarioService.crear(usuarioAprobar.getUsuarioRegistrado());
		model.addObject("usuarios", listaUsuarios());
		
		return model;
	}

	@RequestMapping(value = "/pages/formulario", method = RequestMethod.GET)
	public ModelAndView detalle(@RequestParam("usuario") String id) {

		ModelAndView model = new ModelAndView();
		Usuario usuarioAprobar = usuarioService.buscar(id);
		List<Soporte> soportes = new ArrayList<>(usuarioAprobar.getUsuarioRegistrado().getSoportes());
		model.addObject("usuario", usuarioAprobar);
		model.addObject("soportes", soportes);
		
		return model;
	}
	
	private List<Usuario> listaUsuarios() {
		
		List<Usuario> usuarios;
		
		switch(accionGlobal) {
			case "validar":
				usuarios = usuarioService.listar(estadoService.buscar(2));
				break;
				
			case "comite":
				usuarios = usuarioService.listar(estadoService.buscar(3));
				break;
			
			default:
				usuarios = usuarioService.listar();
				break;
		}
		
		return usuarios;
	}

}
