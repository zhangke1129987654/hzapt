package com.hzapt.admin.config;

import cn.hutool.core.collection.CollUtil;
import com.hzapt.admin.handler.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 拦截器配置类
 *
 * @author LJ
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = CollUtil.toList();
        excludePathPatterns.add("/logout");
        excludePathPatterns.add("/login");
        excludePathPatterns.add("/static/**");

        excludePathPatterns.add("/jinzong/ossVerify");
        excludePathPatterns.add("/jinzong/accountPwdLogin");
        excludePathPatterns.add("/jinzong/mobileCodeLogin");
        excludePathPatterns.add("/jinzong/sendVerifyCode/**");
//        excludePathPatterns.add("/gab/**");

        // 权限校验拦截器
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/mydata/hzapt/**").addResourceLocations("file:/mydata/hzapt/");
    }

}