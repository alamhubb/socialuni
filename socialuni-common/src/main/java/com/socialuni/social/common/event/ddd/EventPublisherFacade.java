package com.socialuni.social.common.event.ddd;

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

    public static void publishEvent(Object event) {
        if(eventPublisher == null){
            eventPublisher = SpringUtil.getBean(EventPublisher.class);
        }
        eventPublisher.publishEvent(event);
    }



}
