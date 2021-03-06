package com.achui.quick.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.achui.quick.domain.User;
import com.achui.quick.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request, Model model){
		
		User user = new User();
		user.setUsername("achui1");
		user.setPassword("123456");
		userService.saveUser(user);
		model.addAttribute("name","achui");
		
		return "index";
	}
}
