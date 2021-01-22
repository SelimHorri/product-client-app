package com.sesame.myapp.pack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {
	
	@GetMapping("/home")
	public String home(@RequestParam(required=false, defaultValue="springBoot default") String monFramework, ModelMap modelMap) {
		
		modelMap.put("monFramework", monFramework);
		
		// System.out.println("\n\n\n"+ request.getParameter("framework") + "\n\n\n");
		
		return "pages/home";
	}
	
}
