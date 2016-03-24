package com.chotoxautinh.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.chotoxautinh.web.model.User;
import com.chotoxautinh.web.util.UserUtils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
  
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
//		System.out.println("-------------------------->  "+ name);
		String password = authentication.getCredentials().toString();
		User user = UserUtils.load(name);
		
//		System.out.println(" found "+ user);
		if(user == null || !password.equals(user.getPassword())) return null;
//		System.out.println("------  >" +user.getRole());

		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		if (user.getRole() == User.STAFF) {
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		} else if (user.getRole() == User.ADMIN) {
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
		  return null;
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		return auth;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
