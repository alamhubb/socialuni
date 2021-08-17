package com.socialuni.center.web.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {
    @Resource
    private UserAuthInterceptor userAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(userAuthInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
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

    //首先，在springboot启动类中，或者配置类中进行配置
    /*@Resource
    RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        StringHttpMessageConverter xmlConverter = new StringHttpMessageConverter();
        //优先xml解析
        List<MediaType> xmlTypes = new ArrayList<>();
        xmlTypes.add(MediaType.APPLICATION_XML);
        xmlTypes.add(MediaType.TEXT_PLAIN);
        xmlConverter.setSupportedMediaTypes(xmlTypes);
        // json
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> jsonTypes = new ArrayList<>();
        jsonTypes.add(MediaType.APPLICATION_JSON);
        jsonTypes.add(MediaType.APPLICATION_JSON_UTF8);
        jsonTypes.add(MediaType.APPLICATION_ATOM_XML);
        jsonTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        jsonTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        jsonTypes.add(MediaType.APPLICATION_PDF);
        jsonTypes.add(MediaType.APPLICATION_RSS_XML);
        jsonTypes.add(MediaType.APPLICATION_XHTML_XML);
        jsonTypes.add(MediaType.IMAGE_GIF);
        jsonTypes.add(MediaType.IMAGE_JPEG);
        jsonTypes.add(MediaType.IMAGE_PNG);
        jsonTypes.add(MediaType.TEXT_EVENT_STREAM);
        jsonTypes.add(MediaType.TEXT_HTML);
        jsonTypes.add(MediaType.TEXT_MARKDOWN);
        jsonTypes.add(MediaType.TEXT_PLAIN);
        jsonTypes.add(MediaType.TEXT_XML);
        jsonConverter.setSupportedMediaTypes(jsonTypes);

        messageConverters.add(xmlConverter);
        messageConverters.add(jsonConverter);
        //form
        messageConverters.add(new FormHttpMessageConverter());

        restTemplate.setMessageConverters(messageConverters);
        //使用build()方法进行获取
        return restTemplate;
    }*/
}