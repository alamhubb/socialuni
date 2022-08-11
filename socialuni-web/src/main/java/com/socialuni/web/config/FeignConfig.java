package com.socialuni.web.config;

import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ConditionalOnClass({Feign.class})
public class FeignConfig implements WebMvcRegistrations {
    private final RequestMappingHandlerMapping requestMappingHandlerMapping = new FeignRequestMappingHandlerMapping();

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return requestMappingHandlerMapping;
    }

    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) &&
                    AnnotationUtils.findAnnotation(beanType, FeignClient.class) == null &&
                    AnnotationUtils.findAnnotation(beanType, Service.class) == null;
        }
    }
}