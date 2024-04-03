package com.coderscampus.miriamassignment14.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String welcome(Model model) {
		return "welcome";
	}
	
	@PostMapping("/setUser")
	public String setUser() {
		return "redirect:/channels";
		
	}

}
