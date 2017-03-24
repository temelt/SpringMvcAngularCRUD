package com.temelt.appname.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.temelt.appname.dto.User;
import com.temelt.appname.service.IUserService;

/**
 * 
 * @author taner.temel
 *
 */
@Service
public class UserService implements IUserService{

	public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return new User("");
        }

        return new User(((UserDetails) authentication.getPrincipal()).getUsername());
	}

}
