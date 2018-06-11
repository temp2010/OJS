package org.udistrital.ojs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	public JavaMailSender emailSender;

	@RequestMapping(value = "/pages/users", method = RequestMethod.GET)
	public ModelAndView listar(@RequestParam Map<String, String> request) {

		if(request.get("volver") == null) {
			accionGlobal = request.get("accion");
		}
		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("rol", auth.getAuthorities());
		model.addObject("usuarios", listaUsuarios());

		return model;
	}

	@RequestMapping(value = "/pages/users", method = RequestMethod.POST)
	public ModelAndView guardar(@RequestParam Map<String, String> request) {

		ModelAndView model = new ModelAndView();
		Usuario usuarioAprobar = usuarioService.buscar(request.get("form-user"));

		Estado estado = eC.obtenerEstado(usuarioAprobar.getUsuarioRegistrado(), request.get("form-action"));
		usuarioAprobar.getUsuarioRegistrado().setEstado(estado);
		if(request.get("form-observation") != "") {
			usuarioAprobar.getUsuarioRegistrado().setObservacion(request.get("form-observation"));
		}
		usuarioService.crear(usuarioAprobar.getUsuarioRegistrado());
		enviarNotificacion(usuarioAprobar, null);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("rol", auth.getAuthorities());
		model.addObject("usuarios", listaUsuarios());
		model.addObject("success", "Registro "+request.get("form-action")+" correctamente!");

		return model;
	}

	@RequestMapping(value = "/pages/formulario", method = RequestMethod.GET)
	public ModelAndView detalle(@RequestParam("usuario") String id) {

		List<Soporte> soportes = null;
		ModelAndView model = new ModelAndView();
		Usuario usuarioAprobar = usuarioService.buscar(id);
		
		if(usuarioAprobar.getUsuarioRegistrado() != null) {
			soportes = new ArrayList<>(usuarioAprobar.getUsuarioRegistrado().getSoportes());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("rol", auth.getAuthorities());
		model.addObject("usuario", usuarioAprobar);
		model.addObject("soportes", soportes);

		return model;
	}

	private List<Usuario> listaUsuarios() {

		List<Usuario> usuarios;

		switch (accionGlobal) {
		case "validar":
			usuarios = usuarioService.listar(estadoService.buscar(2));
			break;
			
		case "comite":
			usuarios = usuarioService.listar(estadoService.buscar(3));
			break;
						
		case "aprobar":
			usuarios = usuarioService.listar(estadoService.buscar(4));
			break;
			
		default:
			usuarios = usuarioService.listar();
			break;
		}
		
		return usuarios;
	}

	public void enviarNotificacion(Usuario usuario, JavaMailSender emailSender) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setCc("temp2010@msn.com");
		message.setSubject("OJS");
		switch(usuario.getUsuarioRegistrado().getEstado().getId()) {
			case 1:
				message.setTo(usuario.getCorreo());
				message.setText("Usuario devuelto! Ingrese a: https://localhost:8443/ojs/register?usuario="+usuario.getIdMD5());
			break;
			
			case 2:
				message.setTo("valid@ojs.co");
				message.setText("Usuario: "+usuario.getNombre()+" registrado correctamente!");
			break;
			
			case 3:
				message.setTo("comite@ojs.co");
				message.setText("Usuario: "+usuario.getNombre()+" aprobado por el evaluador!");
			break;
			
			case 4:
				message.setTo("admin@ojs.co");
				message.setText("Usuario "+usuario.getNombre()+" aprobado por el comité!");
			break;
			
			case 5:
				message.setTo(usuario.getCorreo());
				message.setText("Usuario rechazado!");
			break;

			default:
			break;
		}
		
		if(message.getText() != null && emailSender != null) {
			emailSender.send(message);
		}
		if(message.getText() != null && emailSender == null) {
			this.emailSender.send(message);
		}
	}

}
