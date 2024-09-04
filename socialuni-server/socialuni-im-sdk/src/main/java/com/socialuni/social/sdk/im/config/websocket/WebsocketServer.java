package com.socialuni.social.sdk.im.config.websocket;

import com.socialuni.social.common.api.utils.NumberUtils;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.utils.RedisUtil;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.sdk.im.enumeration.NotifyType;
import com.socialuni.social.sdk.im.notify.NotifyVO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.domain.SocialuniUserOnlineDomain;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class WebsocketServer extends TextWebSocketHandler {
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     * <p>
     * 存uid是因为notify里面的user是uid
     */
    public static final ConcurrentHashMap<String, WebSocketSession> onlineUsersSessionMap = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, String> onlineUsersNamesMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ChannelTopic> onlineUsersChannelTopicMap = new ConcurrentHashMap<>();
    public static final String onlineUsersCountKey = "onlineUsersCount";

    @Resource
    private MessageListenerAdapter messageListener;

    @Resource
    private RedisMessageListenerContainer redisContainer;
    @Resource
    private SocialuniUserOnlineDomain socialuniUserOnlineDomain;

    private static RedisUtil redisUtil;

    private static SocialuniChatUserRepository chatUserRepository;

    @Resource
    public void setChatUserRepository(SocialuniChatUserRepository chatUserRepository) {
        WebsocketServer.chatUserRepository = chatUserRepository;
    }

    @Resource
    public void setRedisUtil(RedisUtil redisUtil) {
        WebsocketServer.redisUtil = redisUtil;
    }


    public static <T> void sendUserCount(Integer userCount, List<String> names) {
        NotifyVO<Map> notifyVO = new NotifyVO<>();

        Map<String, Object> map = new HashMap<>();

        map.put("userCount", userCount);
        map.put("names", names);
        notifyVO.setType(NotifyType.usersInfo);
        notifyVO.setData(map);
        WebsocketServer.sendToAllUsers(notifyVO);
    }

    public static <T> void sendToAllUsers(NotifyVO<T> notify) {
        log.info("fasongle xiaoxi ");
        //发送给所有在线的群组里面的用户
        for (WebSocketSession userSession : onlineUsersSessionMap.values()) {
            if (userSession != null && userSession.isOpen()) {
                try {
                    userSession.sendMessage(notify.toMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 广播信息
     */
    public static <T> void sendToGroupUsers(NotifyVO<T> notify, List<String> userIds) {
        for (String userIdStr : userIds) {
            WebSocketSession userSession = onlineUsersSessionMap.get(userIdStr);
            if (userSession != null && userSession.isOpen()) {
                String sessionUserId = Objects.requireNonNull(userSession.getPrincipal()).getName();
                //如果发送人是自己则不发送
                if (userIdStr.equals(sessionUserId)) {
                    continue;
                }
                //发给不是自己的
                log.info("消息发送用户id:{},sessionId:{}", userIdStr, sessionUserId);
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

    public static void sendMessage(Long userId, NotifyVO notify) {
        String uuid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userId);
        WebSocketSession session = onlineUsersSessionMap.get(uuid);
        if (session == null) {
            return;
        }
        //如果用户在线才发送
        if (session.isOpen()) {
            try {
                session.sendMessage(notify.toMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessage(NotifyVO notify) {
        String userId = notify.getReceiveUserId();
        WebSocketSession session = onlineUsersSessionMap.get(userId);
        if (session == null) {
            return;
        }
        //如果用户在线才发送
        if (session.isOpen()) {
            try {
                session.sendMessage(notify.toMessage());
            } catch (IOException e) {
                e.printStackTrace();
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
            log.info("登录成功:{}", user.getName());
            String userId = user.getName();
            //加入set中
            this.setOnlineUser(userId, session);
            //在线数加1
            socialuniUserOnlineDomain.userOnline(userId);
            //加入set中
            addOnlineCount();
            //加入set中
//            ChannelTopic channelTopic = new ChannelTopic(userId);
//            redisContainer.addMessageListener(messageListener, channelTopic);

//            onlineUsersChannelTopicMap.put(userId, channelTopic);
            if (NumberUtils.strIsAllNumber(userId)) {
//                userService.setUserOnlineTrue(userId);
            }
            log.info("用户标识：{}，Session：{}，在线数量：{}", userId, session.toString(), onlineUsersSessionMap.size());
        }
    }

    private void setOnlineUser(String userId, WebSocketSession session) {
        onlineUsersSessionMap.remove(userId);
        onlineUsersSessionMap.put(userId, session);

        SocialuniUserDo mineUser = SocialuniUserUtil.getUserByUuidAllowNull(userId);
        if (mineUser != null) {
            onlineUsersNamesMap.remove(userId);
            onlineUsersNamesMap.put(userId, mineUser.getNickname());
        }
    }

    private void removeUser(String userId) {
        onlineUsersSessionMap.remove(userId);
        onlineUsersNamesMap.remove(userId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("收到客户端消息：{}", message.getPayload());
        System.out.println(Objects.requireNonNull(session.getPrincipal()).getName());
        socialuniUserOnlineDomain.userOnline(Objects.requireNonNull(session.getPrincipal()).getName());
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
            log.info("handleTextMessage method error：{}", e);
        }*/
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        log.info("收到客户端消息：{}", message.getPayload());
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) {
        log.info("收到客户端消息：{}", message.getPayload());
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
        log.info("连接出错");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("连接已关闭：" + status);
        try {
            Principal user = session.getPrincipal();
            if (user != null && StringUtils.isNotEmpty(user.getName())) {
                String userId = user.getName();
                if (onlineUsersSessionMap.containsKey(userId)) {
                    this.removeUser(userId);
                    socialuniUserOnlineDomain.userOffline(userId);
                    subOnlineCount();
//                    redisContainer.removeMessageListener(messageListener, onlineUsersChannelTopicMap.get(userId));
//                    onlineUsersChannelTopicMap.remove(userId);
                    //从set中删除
                }
                if (NumberUtils.strIsAllNumber(userId)) {
//                    userService.setUserOnlineFalse(userId);
                }
            }
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static synchronized int getOnlineCount() {
//        Object onlineUsersCount = redisUtil.get(WebsocketServer.onlineUsersCountKey);
//        if (onlineUsersCount == null) {
//            return 0;
//        } else {
//            return (Integer) onlineUsersCount;
//        }
        return onlineUsersSessionMap.size();
    }

    public static synchronized void addOnlineCount() {
        int newCount = getOnlineCount();
//        int newCount = onlineUsersCount + 1;
//        redisUtil.set(WebsocketServer.onlineUsersCountKey, newCount);

        List<String> names = new ArrayList<>(onlineUsersNamesMap.values());

        sendUserCount(newCount, names);
    }

    public static synchronized void subOnlineCount() {
        int newCount = getOnlineCount();
//        int newCount = Math.max(onlineUsersCount - 1, 0);
//        redisUtil.set(WebsocketServer.onlineUsersCountKey, newCount);
        List<String> names = new ArrayList<>(onlineUsersNamesMap.values());
        sendUserCount(newCount, names);
    }

    public static synchronized void subOnlineCount(Integer offlineNum) {
        int onlineUsersCount = getOnlineCount();
        redisUtil.set(WebsocketServer.onlineUsersCountKey, Math.max(onlineUsersCount - offlineNum, 0));
    }
}