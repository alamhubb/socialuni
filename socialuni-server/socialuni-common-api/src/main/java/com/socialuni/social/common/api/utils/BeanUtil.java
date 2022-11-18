package com.socialuni.social.common.api.utils;


import cn.hutool.extra.spring.SpringUtil;

import static cn.hutool.extra.spring.SpringUtil.getBeanFactory;

/**
 * 封装获得bean的细节，将来可以用于扩展其他的IOC容器。
 * 将来可能spring也会被替换。
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/9 15:10
 * @since 1.0
 */
public class BeanUtil {
    private BeanUtil(){}

    /**
     *
     * @param name
     * @param <T>
     * @return
     */
    public static Object getBean(String name) {
        return SpringUtil.getBeanFactory().getBean(name);
    }
    public static <T> T getBean(Class<T> clazz) {
        return SpringUtil.getBeanFactory().getBean(clazz);
    }
    public static <T> T getBean(String name, Class<T> clazz) {
        return SpringUtil.getBeanFactory().getBean(name, clazz);
    }

}
