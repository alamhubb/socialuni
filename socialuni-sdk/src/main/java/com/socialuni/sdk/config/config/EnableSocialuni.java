package com.socialuni.sdk.config.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//可以注入tokenkey和token秘钥
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(SocialuniAutoConfiguration.class)
public @interface EnableSocialuni {
}