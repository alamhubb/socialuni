package com.socialuni.social.web.sdk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SocialWebRequestLogConfig implements WebMvcConfigurer {
    @Resource
    private SocialWebRequestLogInterceptor socialWebRequestLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(socialWebRequestLogInterceptor).addPathPatterns("/**");
    }
}