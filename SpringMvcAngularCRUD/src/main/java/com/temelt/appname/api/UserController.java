package com.temelt.appname.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temelt.appname.dto.User;
import com.temelt.appname.service.impl.UserService;

/**
 * 
 * @author taner.temel
 *
 */
@Controller
public class UserController {
	@Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User getCurrentUser() {
        return userService.getUser();
    }
}
