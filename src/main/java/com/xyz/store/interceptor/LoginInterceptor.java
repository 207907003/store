package com.xyz.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器(URL+映射)
     * @return ture放行,false不进行后续操作
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session的uid,如果没有代表没有登录,返回登录页面
        Object uid = request.getSession().getAttribute("uid");
        if(uid==null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;

    }


}
