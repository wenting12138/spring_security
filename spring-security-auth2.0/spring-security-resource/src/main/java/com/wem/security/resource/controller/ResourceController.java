package com.wem.security.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 21:44
 * @ClassName 类名称
 * @Description 类描述
 */
@RestController
public class ResourceController {

    @GetMapping("/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1(){
        return "访问资源r1";
    }

}
