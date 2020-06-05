package com.wen.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 11:57
 * @ClassName 类名称
 * @Description 类描述
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = "text/plain;charset=UTF-8")
    public String login(){
        String username = getUsername();
        return "登录成功: " + username;
    }

    private String getUsername() {

        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取用户的身份
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else {
            username = principal.toString();
        }
        return username;
    }

//    @PreAuthorize("isAnonymous()")
    @PreAuthorize("hasAuthority('p1')")
    @RequestMapping(value = "/r/r1", produces = "text/plain;charset=UTF-8")
    public String r1(){
        return "访问r1";
    }

    @PreAuthorize("hasAuthority('p2')")
    @RequestMapping(value = "/r/r2", produces = "text/plain;charset=UTF-8")
    public String r2(){
        return "访问r2";
    }

}
