package com.wen.security.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 11:45
 * @ClassName 类名称
 * @Description 类描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    // session key
    public static final String SESSION_USER_KEY = "_user";

    // 用户身份信息
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    private Set<String> authorities;

}
