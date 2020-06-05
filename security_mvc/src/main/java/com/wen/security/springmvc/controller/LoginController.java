package com.wen.security.springmvc.controller;

import com.wen.security.springmvc.model.AuthenticationRequest;
import com.wen.security.springmvc.model.UserDto;
import com.wen.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 11:57
 * @ClassName 类名称
 * @Description 类描述
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login", produces = "text/plain;charset=UTF-8")
    public String login(AuthenticationRequest request, HttpSession session){
        UserDto userDto = authenticationService.authentication(request);
        // 存入session
        session.setAttribute(UserDto.SESSION_USER_KEY, userDto);
        return userDto.getUsername() + "登录成功";
    }

    @RequestMapping(value = "/r/r1", produces = "text/plain;charset=UTF-8")
    public String r1(HttpSession session){

        Object attribute = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (attribute == null) {
            return "匿名访问r1";
        }
        UserDto userDto = (UserDto) attribute;
        return "登录名字: " + userDto.getUsername() + "访问r1";
    }
    @RequestMapping(value = "/r/r2", produces = "text/plain;charset=UTF-8")
    public String r2(HttpSession session){

        Object attribute = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (attribute == null) {
            return "匿名访问r2";
        }
        UserDto userDto = (UserDto) attribute;
        return "登录名字: " + userDto.getUsername() + "访问r2";
    }



    @RequestMapping(value = "logout", produces = "text/plain;charset=UTF-8")
    public String logout(HttpSession session){
        session.invalidate();
        return "退出成功";
    }

}
