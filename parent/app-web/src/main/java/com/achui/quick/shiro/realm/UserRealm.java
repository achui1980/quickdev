package com.achui.quick.shiro.realm;

import java.util.HashSet;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.achui.quick.domain.SysUser;
import com.achui.quick.service.AuthService;
import com.achui.quick.service.MyUserService;


public class UserRealm extends AuthorizingRealm{

	@Resource(name="userService")
	private MyUserService userService;
	
	@Resource(name="authService")
	private AuthService authService;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userPasswordToken = (UsernamePasswordToken)token;
		String userName = userPasswordToken.getUsername();
		if(StringUtils.isEmpty(userName)){
			throw new AccountException("user name is null");
		}
		SysUser user = userService.findByUsername(userName);
		if(user == null){
			throw new UnknownAccountException("user not found");
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SysUser user = userService.findByUsername(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet(authService.getRoleString(user)));
        authorizationInfo.setStringPermissions(new HashSet(authService.getPermissions(user)));
        return authorizationInfo;
	}
	
	

}
