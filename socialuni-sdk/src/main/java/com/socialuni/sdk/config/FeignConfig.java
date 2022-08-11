package com.socialuni.sdk.config;

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
            System.out.println(beanType.getName());
            if (beanType.getName().contains("SocialuniTalkAPIImpl")) {

                AnnotationUtils.findAnnotation(beanType, Service.class);

                System.out.println(super.isHandler(beanType));

                System.out.println(AnnotationUtils.findAnnotation(beanType, FeignClient.class) == null);

                System.out.println(AnnotationUtils.findAnnotation(beanType, Service.class) == null);
            }

            return super.isHandler(beanType) &&
                    AnnotationUtils.findAnnotation(beanType, FeignClient.class) == null &&
                    AnnotationUtils.findAnnotation(beanType, Service.class) == null;
        }
    }
}