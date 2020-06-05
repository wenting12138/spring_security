package com.wen.security.springmvc.service;

import com.wen.security.springmvc.model.AuthenticationRequest;
import com.wen.security.springmvc.model.UserDto;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 11:44
 * @ClassName 类名称
 * @Description 类描述
 */
public interface AuthenticationService {

    UserDto authentication(AuthenticationRequest request);
}
