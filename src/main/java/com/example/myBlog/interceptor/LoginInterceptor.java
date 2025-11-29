package com.example.myBlog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从 Session 里尝试获取用户
        Object user = request.getSession().getAttribute("currentUser");

        // 2. 如果获取不到，说明没登录
        if (user == null) {
            // 设置状态码 401 (未授权)
            response.setStatus(401);
            // 咱们手动写个简单的 JSON 返回去，告诉前端要登录
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\":401, \"msg\":\"请先登录！\"}");
            return false; // 🚫 拦截，不放行
        }

        // 3. 登录了，放行
        return true; // ✅ 通过
    }
}