package com.wem.security.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 19:09
 * @ClassName 类名称
 * @Description 类描述
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

}
