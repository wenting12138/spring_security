package com.wen.security.springmvc.interceptor;

import com.wen.security.springmvc.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 12:57
 * @ClassName 类名称
 * @Description 类描述
 */
@Component
public class SimpleAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object o = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (o == null) {
            writeContent(response, "请先登录");
        }
        UserDto userDto = (UserDto) o;
        // 获取用户权限
        String uri = request.getRequestURI();
        if (userDto.getAuthorities().contains("p1") && uri.equals("/r/r1")) {
            return true;
        }
        if (userDto.getAuthorities().contains("p2") && uri.equals("/r/r2")) {
            return true;
        }
        writeContent(response, "没有权限");
        return false;
    }

    // 响应信息给客户端
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(msg);
        printWriter.close();
        response.resetBuffer();
    }
}
