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
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
    @RequestMapping(value="/pages/areas", method=RequestMethod.GET)
    public ModelAndView listar() {
    	ModelAndView model = new ModelAndView();
    	List<Area> areas = areaService.listar();
    	model.addObject ("areas", areas);
        return model;
    }
}
