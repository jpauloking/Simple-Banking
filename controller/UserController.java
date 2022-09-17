package com.simplebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplebanking.model.User;
import com.simplebanking.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUserDetailsView(ModelMap model) {
		User user = userService.getPrincipalUser();
		model.addAttribute("user", user);
		return "user/user_details_view";
	}
	
	@GetMapping("/registration")
	public String getUserCreationForm(ModelMap model) {
		model.addAttribute("user", new User());
		return "user/user_form_view";
	}
	
	@PostMapping("/registration")
	public String postUserCreationForm(User user) {
		userService.saveUser(user);
		return "redirect:/login";
	}
		
}
