package com.wen.security.init;

import com.wen.security.config.WebSecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 13:40
 * @ClassName 类名称
 * @Description 类描述
 */
public class SpringSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SpringSecurityApplicationInitializer() {
        // 当前环境没有用spring 或 spring mvc 需要加上下面
//        super(WebSecurityConfig.class);
    }
}
