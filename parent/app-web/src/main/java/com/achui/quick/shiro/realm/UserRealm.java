package com.achui.quick.shiro.realm;

import javax.annotation.Resource;
import javax.security.auth.login.AccountNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

import com.achui.quick.domain.User;
import com.achui.quick.service.UserService;


public class UserRealm extends AuthenticatingRealm{

	@Resource(name="userService")
	private UserService userService;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userPasswordToken = (UsernamePasswordToken)token;
		String userName = userPasswordToken.getUsername();
		if(StringUtils.isEmpty(userName)){
			throw new AccountException("user name is null");
		}
		User user = userService.findByUsername(userName);
		if(user == null){
			throw new UnknownAccountException("user not found");
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

}
