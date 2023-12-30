package com.socialuni.social.common.sdk.event.ddd;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/20 17:20
 * @since 1.0
 */
@Slf4j
public abstract class EventPublisherFacade {
    private static EventPublisher eventPublisher;

    /**
     * 指定对应的topic标识。 用于同一类对象，不同的增删改的逻辑处理。
     * 是需要再次区分的。
     * @param topicName 标识路径
     * @param event
     */
    public static void publishEvent(String topicName,Object event) {
        if(eventPublisher == null){
            eventPublisher = SpringUtil.getBean(EventPublisher.class);
        }
        eventPublisher.publishEvent(topicName,event);
    }

    /**
     * 简化使用,一类实体逻辑相同的处理
     * @param event
     * @see #publishEvent(String, Object)
     */
    public static void publishEvent(Object event) {
        publishEvent(ClassUtil.getClassName(event,false),event);
    }
}
