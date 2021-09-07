/*
package com.qingchi.web.domain;

import com.qingchi.web.constant.ExpenseType;
import com.qingchi.web.service.ShellOrderService;
import com.socialuni.sdk.constant.OpenChatType;
import com.socialuni.constant.ChatStatus;
import com.socialuni.constant.ChatUserStatus;
import com.socialuni.entity.model.DO.chat.ChatDO;
import com.socialuni.entity.model.DO.chat.ChatUserDO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.repository.ChatRepository;
import com.socialuni.sdk.repository.ChatUserRepository;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.message.chat.ChatRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PayShellOpenChatDomain {
    @Resource
    ChatRepository chatRepository;
    @Resource
    ChatUserRepository chatUserRepository;
//    @Resource
//    FollowRepository socialFollowRepository;
    @Resource
    private ShellOrderService shellOrderService;

    //会话已开启，则可直接发起对话。
    //会华为开启，判断对方是否关注你。
    //未关注，提示开启。

    //否则可以直接进入chat页面。

    //对方关注了你，进入chat页面。有chat返回，没有创建并返回

    //支付创建的时候，需要判断是否已存在（你关注的对方，进入过这个界面，这个时候chat就创建了，所以你进来，他是待开启），所以不需要再次创建了

    //进入chat页面的情况
    //获取chat状态，可以得知，待开启，有个状态，是否需要购买开启，

    //会话未开启，对方为关注你
    //对方已关注你

    //会话已开启

    //发送的时候判断，必须为开启

    //提示是否开启会话，点击发送，的时候，提示对方未关注您，需要支付10个贝壳开启会话，是否支付并开启会话？

    //提示会话未开启，

    //判断是否有对话了，如果有判断状态，没有的话创建，是否付费，应该为前台字段，转vo的时候每次去查询判断，应该是关注的时候去改变状态呢，根据业务决定，应该是
    //转vo的时候去判断，因为点关注的时候大部分没有会话，待开启，成为chatvo的时候需要在这个函数里面处理

    //把自己这边的前台显示改为true，更新update时间
    //还要从chat中寻找，是否已经有了，
    //chat列表中需要在遍历转vo的时候判断，是否需要支付标识，

    //点击一个人，陌生人，点击发送消息。
    //未关注你，进来什么也不提示？提示一下，对方未关注，发送消息需要付费，改为前台显示，提示对方已关注，无需付费请刷新
    //对方已关注，提示会话未开启，点击发送时提示，会话未开启，是否确认开启会话，改为前台显示，开启时，校验对方未关注，需要付费开启，请刷新
    //已有会话，则直接进入，改为前台显示

    @Transactional
    public ResultRO<ChatRO> openChat(UserDO user, UserDO receiveUser, ChatDO chatDO, ChatUserDO chatUserDO, ChatUserDO receiveChatUserDO, boolean needPayOpen) {
        //肯定不能通过 可用状态查询是否显示，
        //要有一个状态判断是否在前台显示，因为有时候开启了，但是前台不显示。你被对方开启
        Date curDate = new Date();
        //chat改为开启
        //开启chat
        //你需要自己的chat为代开起
        chatDO.setStatus(ChatStatus.enable);
        chatDO.setUpdateTime(curDate);
        chatDO = chatRepository.save(chatDO);


        //更改状态返回
        //开启自己的chatUser
        chatUserDO.setStatus(ChatUserStatus.enable);
        chatUserDO.setUpdateTime(curDate);
        //自己的要在前台显示，需要有一个状态控制是否前台显示
//        chatUserDO.setFrontShow(true);

        //开启对方的chatUser
        receiveChatUserDO.setStatus(ChatUserStatus.enable);
        receiveChatUserDO.setUpdateTime(curDate);
//        receiveChatUserDO.setFrontShow(true);
        if (needPayOpen) {
//            chatUserDO.setLastContent("您付费开启了会话");
            chatUserDO.setOpenChatType(OpenChatType.payOpen);

            //需要更改，到时候需要由用户1向用户2发送一条系统默认消息
//            receiveChatUserDO.setLastContent("对方付费开启了和您的会话");
            receiveChatUserDO.setOpenChatType(OpenChatType.receivePayOpen);
        } else {
//            chatUserDO.setLastContent("您开启了会话");
            chatUserDO.setOpenChatType(OpenChatType.normalOpen);

            //需要更改，到时候需要由用户1向用户2发送一条系统默认消息
//            receiveChatUserDO.setLastContent("对方开启了和您的会话");
            receiveChatUserDO.setOpenChatType(OpenChatType.normalReceiveOpen);
        }


        List<ChatUserDO> chatUserDOS = Arrays.asList(chatUserDO, receiveChatUserDO);
        chatUserRepository.saveAll(chatUserDOS);


        if (needPayOpen) {
            //返回
            shellOrderService.createAndSaveContactAndShellOrders(user, receiveUser, ExpenseType.openChat);
        }

        ChatRO chatVO = new ChatRO(chatDO, chatUserDO, true);
        //需要对方的用户名，昵称。会话未开启
        return new ResultRO<>(chatVO);
    }

    //对应的之前的在个人详情页面开启
    */
/*@Transactional
    public ResultVO<ChatVO> payShellOpenChatOnUserDetail(UserDO user, UserDO receiveUser, ChatDO chatDO, ChatUserDO chatUserDO) {
        //肯定不能通过 可用状态查询是否显示，
        //要有一个状态判断是否在前台显示，因为有时候开启了，但是前台不显示。你被对方开启

        //如果为空，则走创建逻辑,付费开启，chat直接开启
        ChatUserDO receiveChatUserDO = null;
        if (chatUserDO == null) {
            chatDO = new ChatDO(ChatType.single, CommonStatus.enable);
            //生成chat
            chatUserDO = new ChatUserDO(chatDO, user.getId(), receiveUser.getId());

            receiveChatUserDO = new ChatUserDO(chatDO, receiveUser.getId(), user.getId());
            //还需要发送一条消息，系统的消息。判断是，自己的这边提示，您付费开启了会话，对方付费开启了和您的会话。
        } else {
            //需要查询出来判断状态，区分返回不同的错误消息
            Optional<ChatDO> chatDOOptional = chatRepository.findById(chatUserDO.getChatId());
            if (!chatDOOptional.isPresent()) {
                QingLogger.logger.error("不存在的chat或者chat状态不正确");
                return new ResultVO<>(ErrorCode.SYSTEM_ERROR);
            }

            chatDO = chatDOOptional.get();

            if (!chatDO.getStatus().equals(CommonStatus.waitOpen)) {
                return new ResultVO<>("会话已开启，请刷新后重试");
            }

            Optional<ChatUserDO> receiveChatUserDOOptional = chatUserRepository.findFirstByChatIdAndUserId(chatDO.getId(), receiveUser.getId());
            if (!receiveChatUserDOOptional.isPresent()) {
                QingLogger.logger.error("chatUser不存在或者状态错误");
                return new ResultVO<>(ErrorCode.SYSTEM_ERROR);
            }

            receiveChatUserDO = receiveChatUserDOOptional.get();

            if (!receiveChatUserDO.getStatus().equals(CommonStatus.waitOpen)) {
                return new ResultVO<>("会话已开启，请刷新后重试");
            }

            Date curDate = new Date();
            //chat改为开启
            //开启chat
            //你需要自己的chat为代开起
            chatDO.setStatus(CommonStatus.enable);
            chatDO.setUpdateTime(curDate);

            //更改状态返回
            //开启自己的chatUser
            chatUserDO.setStatus(CommonStatus.enable);
            chatUserDO.setUpdateTime(curDate);
            //自己的要在前台显示，需要有一个状态控制是否前台显示
            chatUserDO.setFrontShow(true);
            //开启对方的chatUser
            receiveChatUserDO.setStatus(CommonStatus.enable);
            receiveChatUserDO.setUpdateTime(curDate);
            receiveChatUserDO.setFrontShow(true);
        }

        //返回
        shellOrderService.createAndSaveContactAndShellOrders(user, receiveUser, ExpenseType.openChat);

        chatDO = chatRepository.save(chatDO);

        chatUserDO.setLastContent("您付费开启了会话");
        chatUserDO.setOpenChatType(OpenChatType.payOpen);

        receiveChatUserDO.setLastContent("对方付费开启了和您的会话");
        receiveChatUserDO.setOpenChatType(OpenChatType.receivePayOpen);
        List<ChatUserDO> chatUserDOS = Arrays.asList(chatUserDO, receiveChatUserDO);
        chatUserRepository.saveAll(chatUserDOS);

        ChatVO chatVO = new ChatVO(chatDO, chatUserDO);
        //需要对方的用户名，昵称。会话未开启
        return new ResultVO<>(chatVO);
    }*//*

}
*/
