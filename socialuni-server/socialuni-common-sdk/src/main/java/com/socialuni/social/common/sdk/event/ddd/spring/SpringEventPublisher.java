package com.socialuni.social.common.sdk.event.ddd.spring;

import com.socialuni.social.common.sdk.event.ddd.EventConsum;
import com.socialuni.social.common.sdk.event.ddd.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 事件采用spring的机制实现。
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/20 17:35
 * @since 1.0
 */
@Component
//@ConditionalOnMissingBean(EventPublisher.class)
@ConditionalOnProperty(prefix = "socialuni.common.ddd.event", name = "enabled", matchIfMissing = true)
public class SpringEventPublisher implements EventPublisher {
    @Autowired
    private ApplicationEventPublisher delegatApplicationEventPublisher;
    @Autowired
    private List<EventConsum> eventConsumList;
    /**
     * 委托给spring的事件处理
     * @param event the event to publish
     */
    @Override
    public void publishEvent(String topicName,Object event) {
//        delegatApplicationEventPublisher.publishEvent(new SpringDDDEvent(topicName,event));
    }

    /**
     * @see SpringDDDEvent
     * @param event
     */
    @EventListener(classes = {SpringDDDEvent.class})
    public void listen(SpringDDDEvent event) {
        /*Object source = event.getSource();
        String topicName = event.getTopicName();
        // 调用所有项目中定义的消费者
        for (EventConsum eventConsum : eventConsumList) {
            if (eventConsum.canConsum(topicName,source)) {
                eventConsum.consumEvent(source);
            }
        }*/
    }
}
