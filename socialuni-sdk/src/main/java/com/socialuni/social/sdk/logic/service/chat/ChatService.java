package com.socialuni.social.sdk.logic.service.chat;


import com.socialuni.social.sdk.constant.socialuni.ChatStatus;
import com.socialuni.social.sdk.constant.socialuni.ChatType;
import com.socialuni.social.sdk.dao.DO.chat.ChatDO;
import com.socialuni.social.sdk.dao.DO.chat.ChatUserDO;
import com.socialuni.social.sdk.dao.repository.ChatRepository;
import com.socialuni.social.sdk.dao.repository.ChatUserRepository;
import com.socialuni.social.sdk.logic.factory.SocialChatROFactory;
import com.socialuni.social.sdk.logic.service.CreateSingleChatResult;
import com.socialuni.social.sdk.model.RO.message.chat.ChatRO;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Service
public class ChatService {
    @Resource
    ChatRepository chatRepository;
    @Resource
    ChatUserRepository chatUserRepository;

    //获取私聊的chat
    //查看对方主页时
    public ChatRO seeUserDetailGetOrCreateChat(SocialuniUserDo user, Integer receiveUserId) {
        Optional<ChatUserDO> chatUserDOOptional = chatUserRepository.findFirstByChatStatusAndUserIdAndReceiveUserId(ChatStatus.enable, user.getUnionId(), receiveUserId);
        ChatUserDO chatUserDO;
        //如果创建过，则获取。返回
        if (chatUserDOOptional.isPresent()) {
            chatUserDO = chatUserDOOptional.get();
//            Optional<ChatDO> chatDOOptional = chatRepository.findById(chatUserDO.getChatId());
            //如果没创建过，则创建，并返回
        } else {
            CreateSingleChatResult chatResult = this.seeUserDetailCreateChat(user, receiveUserId);
            chatUserDO = chatResult.getMineChatUser();
        }
        ChatRO chatUserVO = SocialChatROFactory.getChatRO(chatUserDO.getChat(), chatUserDO, true);
        return chatUserVO;
    }

    //点击一个人，陌生人，点击发送消息。
    //未关注你，进来什么也不提示？提示一下，对方未关注，发送消息需要付费，改为前台显示，提示对方已关注，无需付费请刷新
    //对方已关注，提示会话未开启，点击发送时提示，会话未开启，是否确认开启会话，改为前台显示，开启时，校验对方未关注，需要付费开启，请刷新
    //已有会话，则直接进入，改为前台显示
    //登录情况下查询用户有权限的chatuser
    //详情页面，需要知道是否关注你了

    public CreateSingleChatResult seeUserDetailCreateChat(SocialuniUserDo user, Integer receiveUserId) {
        ChatDO chat = new ChatDO(ChatType.single);

        //生成chat
        chat = chatRepository.save(chat);

        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        ChatUserDO mineChatUser = new ChatUserDO(chat, user.getUnionId(), receiveUserId);
        //创建时不改为true，详见fronshow字段，修改状态逻辑
//        mineChatUser.setFrontShow(true);

        ChatUserDO receiveChatUser = new ChatUserDO(chat, receiveUserId, user.getUnionId());

        List<ChatUserDO> chatUserDOS = Arrays.asList(mineChatUser, receiveChatUser);
        chatUserRepository.saveAll(chatUserDOS);

        //你需要自己的chat为代开起
        //需要对方的用户名，昵称。会话未开启
        return new CreateSingleChatResult(chat, mineChatUser, receiveChatUser);
    }

    /*public CreateSingleChatResult createSingleChat(UserDO user, UserDO receiveUser) {
        ChatDO chat = new ChatDO(ChatType.single);

        //生成chat
        chat = chatRepository.save(chat);

        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        ChatUserDO mineChatUser = new ChatUserDO(chat, user.getId(), receiveUser.getId());
        //自己的设置为待匹配状态，需要等对方回复后才能改为正常

        //无论是否关注都改为待开启
        //查看对方是否也关注了自己
//        Integer receiveFollowCount = followRepository.countByUserIdAndBeUserIdAndStatus(receiveUser.getId(), user.getId(), CommonStatus.normal);
        //如果对方未关注自己，则不允许直接向对方发送消息，则改为待开启
        mineChatUser.setUnreadNum(0);
        *//*if (receiveFollowCount < 1) {
            //设置为待开启，需要一方发送消息后改为开启
            mineChatUser.setStatus(CommonStatus.waitOpen);
            mineChatUser.setLastContent("会话未开启");
        }*//*

        ChatUserDO receiveChatUser = new ChatUserDO(chat, receiveUser.getId(), user.getId());
        receiveChatUser.setUnreadNum(0);
        //查看自己是否也关注了对方
//        Integer requestFollowCount = followRepository.countByUserIdAndBeUserIdAndStatus(user.getId(), receiveUser.getId(), CommonStatus.normal);
        //如果自己未关注对方，则对方不允许直接发送消息，改为待开启
        *//*if (requestFollowCount < 1) {
            //设置为待开启，需要一方发送消息后改为开启
            receiveChatUser.setStatus(CommonStatus.waitOpen);
            receiveChatUser.setLastContent("会话未开启");
        }*//*
        System.out.println(123);

        List<ChatUserDO> chatUserDOS = Arrays.asList(mineChatUser, receiveChatUser);
        chatUserRepository.saveAll(chatUserDOS);

        //你需要自己的chat为代开起
        //需要对方的用户名，昵称。会话未开启
        return new CreateSingleChatResult(chat, mineChatUser, receiveChatUser);
    }*/


}
