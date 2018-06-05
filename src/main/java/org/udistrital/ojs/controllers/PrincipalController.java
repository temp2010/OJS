package org.udistrital.ojs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.udistrital.ojs.entities.Area;
import org.udistrital.ojs.services.AreaService;

@Controller
public class PrincipalController {
	
	@Autowired
	private AreaService areaService;
	
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public void index() {
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public void login() {
    }
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public void register() {
    	List<Area> listArea = areaService.listar();
    	System.out.println("jopena");
    }
}
