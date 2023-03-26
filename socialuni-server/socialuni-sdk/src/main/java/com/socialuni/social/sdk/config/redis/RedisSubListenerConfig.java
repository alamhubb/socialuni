package com.socialuni.social.sdk.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialuni.social.sdk.config.websocket.WebsocketServer;
import com.socialuni.social.sdk.model.NotifyVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration
public class RedisSubListenerConfig {
    @Resource
    private ObjectMapper objectMapper;

    public static final String allUserKey = "allUser";

    @Bean
    MessageListenerAdapter messageListener() {
        //abstract methods overwrite
        return new MessageListenerAdapter((MessageListener) (message, pattern) -> {
            //解析message获取中的用户信息，和消息信息
            try {
                NotifyVO notifyVO = objectMapper.readValue(message.toString(), NotifyVO.class);
                WebsocketServer.sendMessage(notifyVO.getReceiveUserId().toString(), notifyVO);
//                NewWebSocketServer.sendInfo(message.toString(), notifyVO.getBeUserId().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //message中带user信息，然后根据user推送message
//            String username = chat.getMessages().get(0).getReceiveUser().getPhoneNum();

//            messagingTemplate.convertAndSendToUser(username, "/queue/notifications", chat);
        });
    }

    @Bean
    MessageListenerAdapter allUserListener() {
        //abstract methods overwrite
        return new MessageListenerAdapter((MessageListener) (message, pattern) -> {
            //解析message获取中的用户信息，和消息信息
            try {
                NotifyVO notifyVO = objectMapper.readValue(message.toString(), NotifyVO.class);
                WebsocketServer.sendMessageToAllUsers(notifyVO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(allUserListener(), topic());
        return container;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic(allUserKey);
    }
}