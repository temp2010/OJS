package org.udistrital.ojs.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private UsuarioController uC = new UsuarioController();

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

		Usuario usuario = usuarioService.validar(request.get("form-mail"));
		if (usuario == null) {
			Rol rol = rolService.Buscar(Integer.parseInt(request.get("form-profile")));
			Usuario registrado = new Usuario(rol, request.get("form-name"), request.get("form-mail"));
			usuarioService.crear(registrado);
			UsuarioRegistrado usuarioRegistrado = new UsuarioRegistrado(registrado.getId(),
					eC.obtenerEstado(new UsuarioRegistrado(), ""),
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
			registrado.setUsuarioRegistrado(usuarioRegistrado);
			uC.enviarNotificacion(registrado, emailSender);
			model.addObject("success", "Usuario registrado correctamente!");
		} else {
			model.addObject("error", "El usuario ya existe!");
		}

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout() {
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam Map<String, String> request) {

		ModelAndView model = new ModelAndView();
		Usuario usuarioAprobar = null;

		//Editar el registro
		if(request.get("usuario") != null) {
			usuarioAprobar = usuarioService.buscar(request.get("usuario"));
		}
		model.addObject("areas", areaService.listar());
		model.addObject("roles", rolService.listarPublico());
		model.addObject("usuario", usuarioAprobar);

		return model;
	}

	@RequestMapping(value = "/pages/index")
	public ModelAndView principal() {

		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("rol", auth.getAuthorities());

		return model;
	}
	
	@RequestMapping(value = "/pages/descargar", method = RequestMethod.GET)
	public void descargar(@RequestParam("archivo") String archivo, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""+ archivo + "\"");
		FileInputStream fileInputStream = new FileInputStream(CARPETA + archivo);
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}

	private void guardarArchivo(MultipartFile archivo) throws IOException {
		File destinoArchivo = new File(CARPETA + archivo.getOriginalFilename());
		archivo.transferTo(destinoArchivo);
	}

}
