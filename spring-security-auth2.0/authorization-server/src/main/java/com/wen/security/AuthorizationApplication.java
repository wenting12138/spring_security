package com.wen.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 19:01
 * @ClassName 类名称
 * @Description 类描述
 */
@EnableHystrix
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.wen.security"})
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}
