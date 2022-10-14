package com.socialuni.social.common.enumeration;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/14 10:07
 * @since 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable
public @interface PublishDataCacheable {
    @AliasFor(annotation = Cacheable.class)
    String[] value() default {};
    @AliasFor(annotation = Cacheable.class)
    String[] cacheNames() default {};
    @AliasFor(annotation = Cacheable.class)
    String key() default "";
    @AliasFor(annotation = Cacheable.class)
    String keyGenerator() default "";
    @AliasFor(annotation = Cacheable.class)
    String cacheManager() default "";
    @AliasFor(annotation = Cacheable.class)
    String cacheResolver() default "";
    @AliasFor(annotation = Cacheable.class)
    String condition() default "";
    @AliasFor(annotation = Cacheable.class)
    String unless() default "";
    @AliasFor(annotation = Cacheable.class)
    boolean sync() default false;


    Class[] modelClass() default {};
    String[] modelClassName() default {};

}
