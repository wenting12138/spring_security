package com.wen.security.springmvc.model;

import lombok.Data;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 11:45
 * @ClassName 类名称
 * @Description 类描述
 */
@Data
public class AuthenticationRequest {

    // 认证请求的参数
    private String username;

    private String password;

}
