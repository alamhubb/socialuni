package com.socialuni.social.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SocialuniWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private HandlerInterceptor socialuniWebInterceptor;
    @Autowired(required = false)
    @Qualifier("tanceHandlerInterceptor")
    private HandlerInterceptor tanceHandlerInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
//                .allowedOrigins("*")
                // 改成下面这个。
                // When allowCredentials is true, allowedOrigins cannot contain the special value "*" since that cannot be set on the "Access-Control-Allow-Origin" response header. To allow credentials to a set of origins, list them explicitly or consider using "allowedOriginPatterns" instead.
                .allowedOriginPatterns("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                .allowedHeaders("*")
                //设置前台可读取的头请求
//                .exposedHeaders(HttpHeaders.SET_COOKIE, "haha")
                //跨域允许时间
                .maxAge(3600);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(socialuniWebInterceptor).addPathPatterns("/**");
        /**
         * 拦截器按照顺序执行
         *addPathPatterns 用于添加拦截规则
         *excludePathPatterns 用于排除拦截
         */
        if(tanceHandlerInterceptor != null){
            registry.addInterceptor(tanceHandlerInterceptor)
//                    .excludePathPatterns("/public/**")
                    .addPathPatterns("/**")
                    ;
        }
    }
}