package com.achui.quick.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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

import com.achui.quick.constants.ServiceConstants;
import com.achui.quick.db.ColumnMeta;
import com.achui.quick.db.DBUtil;
import com.achui.quick.domain.Json;
import com.achui.quick.domain.SysUser;
import com.achui.quick.rest.User;
import com.achui.quick.service.MyUserService;
import com.achui.quick.util.FreemarkerHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.ClassPath;
import com.sun.research.ws.wadl.Param;

@Controller
@RequestMapping("/")
public class IndexController {

	@Resource
	DataSource dataSource;
	
	@Autowired
	private MyUserService userService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request, Model model){
		
		model.addAttribute("name","achui");
		
		return "framework";
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
	public @ResponseBody User getUser(@PathVariable("id") Integer id){
		User user = new User();
		user.setId(id);
		user.setUsername("achui");
		user.setPassword("123456");
		return user;
	}
	
	@RequestMapping(value="/user/list" , method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Json getUserList(HttpServletRequest request){
		Json json = new Json();
		String count = request.getParameter("count");
		String start = request.getParameter("start");
		if(StringUtils.isEmpty(count)){ 
			count = "100";
			json.setTotal_count(100);
		}
		
		if(StringUtils.isEmpty(start)) start = "0";
		Pageable page = new PageRequest(Integer.valueOf(start), Integer.valueOf(count));
		
		List userList = userService.findAll("select obj from AchuiUser obj ", page, (Map<String, Object>)null);
		json.setData(userList);
		json.setPos(Integer.valueOf(start));
		json.setTotal_count(100);
		return json;
		
	}
	@RequestMapping(value="/custom")
	public String custom(HttpServletRequest request, Model model){
		
//		String userName = request.getParameter("username");
//		String password = request.getParameter("password");
//		SecurityUtils.getSubject().login(new UsernamePasswordToken(userName,password));
//		model.addAttribute("name","index");
		return "custom";
	}
	
	@RequestMapping(value="/mapping")
	public String mapping(HttpServletRequest request, Model model){
		return "mapping";
	}
	
	@RequestMapping(value="/autolist")
	public void autoList(HttpServletRequest request,
			HttpServletResponse response, Model model){
		try {
			String domain = request.getParameter("domain");
			Class cls = Class.forName(ServiceConstants.SERVICE_DOMAIN_PACKAGE+"."+domain);
			FreemarkerHelper viewEngine = new FreemarkerHelper();
			Map<String, ColumnMeta> metaInfo = DBUtil.getMetaInfo(dataSource, cls);
			List<ColumnMeta> list = Lists.newArrayList();
			list.addAll(metaInfo.values());
			Map<String, Object> paras = Maps.newHashMap();
			paras.put("columnInfoList", list);
			paras.put("domain", domain);
			paras.put("serviceName","myuserService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/testftl/{domain}")
	public String testftl(@PathVariable("domain") String domain, HttpServletRequest request, Model model){
		try{
			String serviceName = domain + ServiceConstants.SERVICE_NAME_SUBFIX;
			domain = ServiceConstants.DOMAIN_PREFIX+StringUtils.capitalize(domain);
			Class cls = Class.forName(ServiceConstants.SERVICE_DOMAIN_PACKAGE+"."+domain);
			String serverName = request.getContextPath();
			Map<String, ColumnMeta> metaInfo = DBUtil.getMetaInfo(dataSource, cls);
			List<ColumnMeta> list = Lists.newArrayList();
			list.addAll(metaInfo.values());
			model.addAttribute("columnInfoMap", metaInfo);
			model.addAttribute("domain", domain);
			model.addAttribute("serviceName",serviceName);
			model.addAttribute("ctx", serverName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "test";
	}
}
