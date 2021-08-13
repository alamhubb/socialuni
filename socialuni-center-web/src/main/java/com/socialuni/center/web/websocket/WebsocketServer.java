/*
package com.socialuni.center.web.websocket;


import com.qingchi.web.manage.CenterUserDetailManage;
import com.socialuni.center.web.serive.UserService;
import com.socialuni.sdk.model.NotifyVO;
import com.socialuni.sdk.redis.RedisUtil;
import com.socialuni.sdk.utils.IntegerUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebsocketServer extends TextWebSocketHandler {
    public static final Logger logger = LoggerFactory.getLogger(WebsocketServer.class);

    */
/**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     *//*

    public static final ConcurrentHashMap<String, WebSocketSession> onlineUsersSessionMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ChannelTopic> onlineUsersChannelTopicMap = new ConcurrentHashMap<>();
    public static final String onlineUsersCountKey = "onlineUsersCount";

    @Resource
    private UserService userService;
    @Resource
    private CenterUserDetailManage centerUserDetailManage;

    @Resource
    private MessageListenerAdapter messageListener;

    @Resource
    private RedisMessageListenerContainer redisContainer;

    private static RedisUtil redisUtil;

    @Resource
    public void setRedisUtil(RedisUtil redisUtil) {
        WebsocketServer.redisUtil = redisUtil;
    }

    */
/**
     * 广播信息
     *//*

    public static void sendMessageToAllUsers(NotifyVO notify) {
        String userId = notify.getUser().getId().toString();
        for (WebSocketSession userSession : onlineUsersSessionMap.values()) {
            if (userSession != null && userSession.isOpen()) {
                String sessionUserId = Objects.requireNonNull(userSession.getPrincipal()).getName();
                if (!userId.equals(sessionUserId)) {
                    //如果用户在线才发送
                    //有不为数字的代表是没登陆的用户才发送
                    try {
                        userSession.sendMessage(notify.toMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void sendMessage(String userId, NotifyVO notify) {
        WebSocketSession session = onlineUsersSessionMap.get(userId);
        //如果用户在线才发送
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(notify.toMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Principal user = session.getPrincipal();
        if (user != null && StringUtils.isNotEmpty(user.getName())) {
            String userId = user.getName();
            if (onlineUsersSessionMap.containsKey(userId)) {
                onlineUsersSessionMap.remove(userId);
                //加入set中
            } else {
                //加入set中
                addOnlineCount();
                //在线数加1
            }
            //加入set中
            ChannelTopic channelTopic = new ChannelTopic(userId);
            redisContainer.addMessageListener(messageListener, channelTopic);
            onlineUsersSessionMap.put(userId, session);
            onlineUsersChannelTopicMap.put(userId, channelTopic);
            if (IntegerUtils.strIsAllNumber(userId)) {
                centerUserDetailManage.updateUserOnlineFlag(userId, true);
            }
            logger.debug("用户标识：{}，Session：{}，在线数量：{}", userId, session.toString(), onlineUsersChannelTopicMap.size());
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        logger.debug("收到客户端消息：{}", message.getPayload());
        */
/*JSONObject msgJson = JSONObject.parseObject(message.getPayload());
        String to = msgJson.getString("to");
        String msg = msgJson.getString("msg");
        WebSocketMessage<?> webSocketMessageServer = new TextMessage("server:" + message);
        try {
            session.sendMessage(webSocketMessageServer);
            if ("all".equals(to.toLowerCase())) {
                sendMessageToAllUsers(new TextMessage(getUserId(session) + ":" + msg));
            } else {
                sendMessageToUser(to, new TextMessage(getUserId(session) + ":" + msg));
            }
        } catch (IOException e) {
            logger.debug("handleTextMessage method error：{}", e);
        }*//*

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        if (session.isOpen()) {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.debug("连接出错");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.debug("连接已关闭：" + status);
        try {
            Principal user = session.getPrincipal();
            if (user != null && StringUtils.isNotEmpty(user.getName())) {
                String userId = user.getName();
                if (onlineUsersSessionMap.containsKey(userId)) {
                    onlineUsersSessionMap.remove(userId);
                    redisContainer.removeMessageListener(messageListener, onlineUsersChannelTopicMap.get(userId));
                    onlineUsersChannelTopicMap.remove(userId);
                    //从set中删除
                    subOnlineCount();
                }
                if (IntegerUtils.strIsAllNumber(userId)) {
                    centerUserDetailManage.updateUserOnlineFlag(userId, true);
                }
            }
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    */
/**
     * 广播信息
     *//*

    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        */
/*Set<String> userIds = users.keySet();
        WebSocketSession session = null;
        for (String userId : userIds) {
            try {
                session = users.get(userId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                } else {
                    logger.debug("客户端:{},已断开连接，发送消息失败", userId);
                }
            } catch (IOException e) {
                logger.debug("sendMessageToAllUsers method error：{}", e);
                allSendSuccess = false;
            }
        }*//*


        return allSendSuccess;
    }

    public static synchronized int getOnlineCount() {
        Object onlineUsersCount = redisUtil.get(WebsocketServer.onlineUsersCountKey);
        if (onlineUsersCount == null) {
            return 0;
        } else {
            return (Integer) onlineUsersCount;
        }
    }

    public static synchronized void addOnlineCount() {
        int onlineUsersCount = getOnlineCount();
        redisUtil.set(WebsocketServer.onlineUsersCountKey, onlineUsersCount + 1);
    }

    public static synchronized void subOnlineCount() {
        int onlineUsersCount = getOnlineCount();
        redisUtil.set(WebsocketServer.onlineUsersCountKey, Math.max(onlineUsersCount - 1, 0));
    }

    public static synchronized void subOnlineCount(Integer offlineNum) {
        int onlineUsersCount = getOnlineCount();
        redisUtil.set(WebsocketServer.onlineUsersCountKey, Math.max(onlineUsersCount - offlineNum, 0));
    }
}*/
