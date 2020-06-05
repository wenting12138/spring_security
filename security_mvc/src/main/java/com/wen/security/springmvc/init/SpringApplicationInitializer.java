package com.wen.security.springmvc.init;

import com.wen.security.springmvc.config.ApplicationConfig;
import com.wen.security.springmvc.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 11:32
 * @ClassName 类名称
 * @Description 类描述
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 加载spring容器
        return new Class[]{ApplicationConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // servletContext
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // url-mapping
        return new String[]{"/"};
    }
}
