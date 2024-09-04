package com.socialuni.social.tance.sdk.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SocialuniNoUseFeignAspect {
    //使用了此注解，则不会触发统一切的统一逻辑，一般非查询需要此配置
}