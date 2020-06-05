package com.wen.security.springmvc.service;

import com.wen.security.springmvc.model.AuthenticationRequest;
import com.wen.security.springmvc.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 11:48
 * @ClassName 类名称
 * @Description 类描述
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static ConcurrentHashMap<String, UserDto> users = new ConcurrentHashMap<>();

    static {
        Set<String> p1 = new HashSet<>();
        p1.add("p1");
        Set<String> p2 = new HashSet<>();
        p2.add("p2");
        users.put("zhangsan", new UserDto("1", "zhangsan", "666", "zhangsan","110", p1));
        users.put("lisi", new UserDto("2", "lisi", "888", "lisi","188", p2));
    }

    /**
     *  校验用户是否合法
     * @param request
     * @return
     */
    @Override
    public UserDto authentication(AuthenticationRequest request) {

        if (request == null || StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getPassword())) {
            throw new RuntimeException("账号和密码为空");
        }

        UserDto userDto = users.get(request.getUsername());
        if (userDto == null) {
            throw new RuntimeException("账号或密码错误");
        }

        if (!userDto.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }
        userDto.setPassword("");
        return userDto;
    }

}
