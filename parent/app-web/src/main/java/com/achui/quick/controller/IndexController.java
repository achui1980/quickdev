package com.achui.quick.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achui.quick.domain.SysUser;
import com.achui.quick.service.MyUserService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private MyUserService userService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request, Model model){
		
		model.addAttribute("name","achui");
		
		return "login";
	}
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model){
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		SecurityUtils.getSubject().login(new UsernamePasswordToken(userName,password));
		model.addAttribute("name","index");
		return "index";
	}
	
	@RequestMapping(value="/user/{id}" , method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SysUser getUser(@PathVariable("id") Integer id){
		SysUser user = new SysUser();
		user.setId(id);
		user.setUsername("achui");
		user.setPassword("123456");
		return user;
	}
	
	@RequestMapping(value="/user/list" , method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SysUser> getUserList(HttpServletRequest request){
		String count = request.getParameter("count");
		String start = request.getParameter("start");
		if(StringUtils.isEmpty(count)) count = "1";
		if(StringUtils.isEmpty(start)) start = "0";
		Pageable page = new PageRequest(Integer.valueOf(start), Integer.valueOf(count));
		return userService.findAll("select obj from SysUser obj ", page, (Map<String, Object>)null);
	}
}
