package com.achui.quick.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
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
		
		model.addAttribute("name","achui");
		
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model){
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		SecurityUtils.getSubject().login(new UsernamePasswordToken(userName,password));
		model.addAttribute("name","login");
		return "login";
	}
}
