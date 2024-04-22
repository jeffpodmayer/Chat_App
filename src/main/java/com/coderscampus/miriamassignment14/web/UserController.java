package com.coderscampus.miriamassignment14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.miriamassignment14.domain.User;
import com.coderscampus.miriamassignment14.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String welcome(Model model) {
		return "welcome";
	}
	
	@PostMapping("/setUser")
	public String setUser(@RequestParam String username) {
		User user = userService.findByUsername(username).orElse(new User());
		user.setUsername(username);
		userService.save(user);
		return "redirect:/channels";
		
	}

}
