package com.socialuni.social.common.sdk.event.ddd.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/23 16:26
 * @since 1.0
 */
public class SpringDDDEvent extends ApplicationEvent {
    public SpringDDDEvent(Object source) {
        super(source);
    }
}
