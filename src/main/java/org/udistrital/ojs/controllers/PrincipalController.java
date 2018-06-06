package org.udistrital.ojs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.udistrital.ojs.entities.Area;
import org.udistrital.ojs.services.AreaService;

@Controller
public class PrincipalController {

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void index() {
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public void saveRegister() {
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
		List<Area> areas = areaService.listar();
		model.addObject("areas", areas);
		return model;
	}

	@RequestMapping(value = "/pages/layout", method = RequestMethod.POST)
	public void principal() {
	}

}
