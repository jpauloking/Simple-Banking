package com.simplebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplebanking.model.User;
import com.simplebanking.service.UserService;

@Controller
@RequestMapping
public class DefaultController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getDashboard(ModelMap model) {
		return "landing_view";
	}
	
	@GetMapping("/login")
	public String getLoginView(ModelMap model) {
		model.addAttribute("user", new User());
		return "login_view";
	}
	
	@GetMapping("/logout-confirm")
	public String getLogoutView(ModelMap model) {
		model.addAttribute("user", userService.getPrincipalUser());
		return "logout_confirm_view";
	}
}
