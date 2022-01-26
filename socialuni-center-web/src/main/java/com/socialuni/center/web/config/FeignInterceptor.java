package com.socialuni.center.web.config;

import com.socialuni.social.constant.SocialFeignHeaderName;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

//开发环境访问线上环境需要
@Slf4j
@Component
@ConditionalOnClass(RequestInterceptor.class)
public class FeignInterceptor implements RequestInterceptor {
    @Value("${socialuni.secret-key}")
    private String socialDevSecretKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(SocialFeignHeaderName.socialSecretKeyHeaderName, socialDevSecretKey);
    }
}