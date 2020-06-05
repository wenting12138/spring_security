package com.wen.security.service;

import com.wen.security.model.UerDto;
import com.wen.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 15:15
 * @ClassName 类名称
 * @Description 类描述
 */
@Service
@Slf4j
public class DbUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     *  根据账号查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 连接数据库, 查询用户信息
        log.info("username: " + username);
        UerDto user = userRepository.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        List<String> list = userRepository.findUserPermission(user.getId());
        String[] per = new String[list.size()];
        list.toArray(per);
        UserDetails details = User.withUsername(user.getUsername()).password(user.getPassword()).authorities(per).build();
        return details;
    }
}
