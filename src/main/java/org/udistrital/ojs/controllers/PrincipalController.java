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

/**
 * PrincipalController: Atiende las peticiones realizadas antes del ingreso de un usuario
 *
 * @author José Daniel Peña
 * @author Cristian Nariño
 * @version 1.0
 */
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

	/**
	 * Escucha la peticion inicial de ingreso al OJS
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void index() {
		System.out.println("a");
	}

	/**
	 * Escucha las peticiones de envio del formulario de registro de usuario
	 * 
	 * @param soporteArchivos: Listado de archivos de soportes
	 * @param request: Datos del registro
	 * @return model: Objeto con los datos para la vista
	 */
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView guardarRegistro(@RequestParam("form-support") MultipartFile[] soporteArchivos,
			@RequestParam Map<String, String> request) throws IOException {

		ModelAndView model = new ModelAndView();
		EstadoController eC = new EstadoController();
		Usuario registrado;

		Usuario usuario = usuarioService.validar(request.get("form-mail"));
		Rol rol = rolService.Buscar(Integer.parseInt(request.get("form-profile")));
		if (usuario == null) {	
			registrado = new Usuario(rol, request.get("form-name"), request.get("form-mail"));
			// Guarda el usuario
			usuarioService.crear(registrado);
			UsuarioRegistrado usuarioRegistrado = new UsuarioRegistrado(registrado.getId(),
					eC.obtenerEstado(new UsuarioRegistrado(), ""),
					areaService.buscar(Integer.parseInt(request.get("form-area"))), request.get("form-professional"));
			if (soporteArchivos.length == 0) {
				usuarioService.crear(usuarioRegistrado);
			} else {
				usuarioRegistrado.setTematica(request.get("form-thematics"));
				usuarioService.crear(usuarioRegistrado);
				// Guarda cada uno de los soportes
				for (MultipartFile archivo : soporteArchivos) {
					guardarArchivo(archivo);
					Soporte soporte = new Soporte(registrado.getId(), archivo.getOriginalFilename());
					soporteService.crear(soporte);
				}
			}
			// Guarda los datos adicionales del registro
			registrado.setUsuarioRegistrado(usuarioRegistrado);
			model.addObject("success", "Usuario registrado correctamente!");
		} else {
			usuario.setRol(rol);
			usuario.setNombre(request.get("form-name"));
			usuario.setCorreo(request.get("form-mail"));
			// Actualizar los datos del usuario
			usuarioService.crear(usuario);
			usuario.getUsuarioRegistrado().setEstado(eC.obtenerEstado(new UsuarioRegistrado(), ""));
			usuario.getUsuarioRegistrado().setArea(areaService.buscar(Integer.parseInt(request.get("form-area"))));
			usuario.getUsuarioRegistrado().setPerfil(request.get("form-professional"));
			if (soporteArchivos.length == 0) {
				usuarioService.crear(usuario.getUsuarioRegistrado());
			} else {
				usuario.getUsuarioRegistrado().setTematica(request.get("form-thematics"));
				usuarioService.crear(usuario.getUsuarioRegistrado());
				soporteService.borrar(usuario.getId());
				// Guarda cada uno de los soportes
				for (MultipartFile archivo : soporteArchivos) {
					guardarArchivo(archivo);
					Soporte soporte = new Soporte(usuario.getId(), archivo.getOriginalFilename());
					soporteService.crear(soporte);
				}
			}
			registrado = usuario;
			model.addObject("success", "Usuario actualizado correctamente!");
		}
		// Envia la notificacion
		uC.enviarNotificacion(registrado, emailSender);
		
		return model;
	}

	/**
	 * Escucha las peticiones para mostrar la pantalla de ingreso
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
	}

	/**
	 * Escucha las peticiones para mostrar la pantalla de Registro
	 * @param request: parametros para validar creacion/edición de un registro
	 * @return model: Objeto con los datos para la vista
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam Map<String, String> request) {

		ModelAndView model = new ModelAndView();
		Usuario usuarioAprobar = null;

		//Editar el registro
		if(request.get("usuario") != null) {
			usuarioAprobar = usuarioService.buscar(request.get("usuario"));
		}
		// Cargar listado de areas profesionales
		model.addObject("areas", areaService.listar());
		// Cargar listado Perfiles publicos
		model.addObject("roles", rolService.listarPublico());
		model.addObject("usuario", usuarioAprobar);

		return model;
	}

	/**
	 * Escucha las peticiones para mostrar la pantalla principal
	 * @return model: Objeto con los datos para la vista
	 */
	@RequestMapping(value = "/pages/index")
	public ModelAndView principal() {

		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("rol", auth.getAuthorities());

		return model;
	}
	
	/**
	 * Escucha las peticiones para descarga de archivos
	 * @param archivo: Nombre del archivo para descargar
	 * @param response: variable de respuesta para colocar la cabezeras de la petición
	 */
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

	/**
	 * Funcion que guarda los archivos (soportes) en una carpeta del sistema
	 * @param archivo: Objeto archivo para guardar
	 */
	private void guardarArchivo(MultipartFile archivo) throws IOException {
		File destinoArchivo = new File(CARPETA + archivo.getOriginalFilename());
		archivo.transferTo(destinoArchivo);
	}

}
