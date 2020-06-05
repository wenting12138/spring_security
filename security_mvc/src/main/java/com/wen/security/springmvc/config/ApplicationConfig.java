package com.wen.security.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 11:22
 * @ClassName 类名称
 * @Description 类描述
 */
@Configuration
@ComponentScan(basePackages = {"com.wen.security.springmvc"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})})
public class ApplicationConfig {
}
