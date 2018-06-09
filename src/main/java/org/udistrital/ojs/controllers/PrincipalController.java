package org.udistrital.ojs.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.udistrital.ojs.entities.Area;
import org.udistrital.ojs.entities.Rol;
import org.udistrital.ojs.entities.Usuario;
import org.udistrital.ojs.entities.UsuarioRegistrado;
import org.udistrital.ojs.services.AreaService;
import org.udistrital.ojs.services.RolService;
import org.udistrital.ojs.services.UsuarioService;

@Controller
public class PrincipalController {

	@Autowired
	private AreaService areaService;

	@Autowired
	private RolService rolService;
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void index() {
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView guardarRegistro(@RequestParam("form-support") MultipartFile fileNus[],
			@RequestParam Map<String, String> request) {

		ModelAndView model = new ModelAndView();
		estadoController eC = new estadoController();

		Usuario escritor = new Usuario(rolService.Buscar(Integer.parseInt(request.get("form-profile"))),
				request.get("form-name"), request.get("form-mail"));
		usuarioService.crear(escritor);
		UsuarioRegistrado escritorRegistrado = new UsuarioRegistrado(escritor.getId(), eC.obtenerEstado(null),
				areaService.buscar(Integer.parseInt(request.get("form-area"))), request.get("form-professional"));
		usuarioService.crear(escritorRegistrado);
		model.addObject("success", "Usuario registrado correctamente!");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout() {
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.addObject("areas", areaService.listar());
		model.addObject("roles", rolService.listarPublico());
		return model;
	}

	@RequestMapping(value = "/pages/layout", method = RequestMethod.POST)
	public void principal() {
	}

}
