package com.xyz.store.interceptor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
@Component
public class LoginInterceptorConfigure implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user_image/**").addResourceLocations("file:C:/Users/Administrator/IdeaProjects/store/src/main/resources/static/user_image/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //使用list集合写拦截白名单
        List<String> patterns =new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/**");
        patterns.add("/user_image/**");
        patterns.add("/address/**");
        patterns.add("/products/**");
        //创建拦截器对象
        HandlerInterceptor interceptor=new LoginInterceptor();
        //白名单
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
