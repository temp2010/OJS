package org.udistrital.ojs.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.udistrital.ojs.models.Rol;
import org.udistrital.ojs.models.Soporte;
import org.udistrital.ojs.models.Usuario;
import org.udistrital.ojs.models.UsuarioRegistrado;
import org.udistrital.ojs.services.AreaService;
import org.udistrital.ojs.services.RolService;
import org.udistrital.ojs.services.SoporteService;
import org.udistrital.ojs.services.UsuarioService;

@Controller
public class PrincipalController {

	private static final String CARPETA = "D:/archivos/";

	@Autowired
	private AreaService areaService;

	@Autowired
	private RolService rolService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SoporteService soporteService;
	
	@Autowired
    public JavaMailSender emailSender;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void index() {
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView guardarRegistro(@RequestParam("form-support") MultipartFile[] soporteArchivos,
			@RequestParam Map<String, String> request) throws IOException {

		ModelAndView model = new ModelAndView();
		EstadoController eC = new EstadoController();

		Rol rol = rolService.Buscar(Integer.parseInt(request.get("form-profile")));
		Usuario registrado = new Usuario(rol, request.get("form-name"), request.get("form-mail"));
		usuarioService.crear(registrado);
		UsuarioRegistrado usuarioRegistrado = new UsuarioRegistrado(registrado.getId(), eC.obtenerEstado(new UsuarioRegistrado(), ""),
				areaService.buscar(Integer.parseInt(request.get("form-area"))), request.get("form-professional"));
		if (soporteArchivos.length == 0) {
			usuarioService.crear(usuarioRegistrado);			
		} else {
			usuarioRegistrado.setTematica(request.get("form-thematics"));
			usuarioService.crear(usuarioRegistrado);
			for (MultipartFile archivo : soporteArchivos) {
				guardarArchivo(archivo);
				Soporte soporte = new Soporte(registrado.getId(), archivo.getOriginalFilename());
				soporteService.crear(soporte);
			}
		}
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo("temp2010@msn.com"); 
        message.setSubject("OJS"); 
        message.setText("Usuario registrado correctamente!");
        emailSender.send(message);
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

	@RequestMapping(value = "/pages/index")
	public void principal() {
	}

	private void guardarArchivo(MultipartFile archivo) throws IOException {
		File destinoArchivo = new File(CARPETA + archivo.getOriginalFilename());
		archivo.transferTo(destinoArchivo);
	}

}
