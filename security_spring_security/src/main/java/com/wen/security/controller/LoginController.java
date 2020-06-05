package com.wen.security.controller;

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

    @RequestMapping(value = "/login-success", produces = "text/plain;charset=UTF-8")
    public String login(){
        return "登录成功";
    }
    @RequestMapping(value = "/r/r1", produces = "text/plain;charset=UTF-8")
    public String r1(){
        return "访问r1";
    }
    @RequestMapping(value = "/r/r2", produces = "text/plain;charset=UTF-8")
    public String r2(){
        return "访问r2";
    }

}
