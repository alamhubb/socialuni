package com.socialuni.social.sdk.im.logic.service.chat;


import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.im.api.model.QO.SocialuniChatCreateQO;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageReceiveDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageReceiveRepository;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.enumeration.MessageStatus;
import com.socialuni.social.sdk.im.logic.domain.ChatQueryDomain;
import com.socialuni.social.sdk.im.logic.entity.SocialuniChatEntity;
import com.socialuni.social.sdk.im.logic.foctory.SocialChatROFactory;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatUserDOFactory;
import com.socialuni.social.im.api.model.QO.chat.ChatReadVO;
import com.socialuni.social.im.api.model.QO.chat.ChatRemoveVO;
import com.socialuni.social.im.api.model.QO.chat.OpenChatVO;
import com.socialuni.social.sdk.im.utils.SocialuniChatDOUtil;
import com.socialuni.social.sdk.im.utils.SocialuniChatUserDOUtil;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Service
public class ChatService {
    @Resource
    SocialuniChatRepository chatRepository;
    @Resource
    SocialuniChatUserRepository chatUserRepository;

    @Resource
    ChatQueryDomain chatQueryDomain;

    @Resource
    private SocialuniMessageReceiveRepository messageReceiveRepository;
    //一个陌生用户，他允许陌生人消息。
    //点击消息，就可以直接进入消息页面。
    //这时候是否创建 chat，还是发了消息再创建。
    //那这时候他会不会添加到你的 chat列表，
    //那总得来说，进入这个页面的时候就应该创建了。
    //那如果后来，他又开启了非好友禁止发送消息呢。所以还是应该发送消息的时候校验。


    @Transactional
    public ResultRO<Void> readChatMessages(ChatReadVO chatVO) {
        String chatUuid = chatVO.getChatId();

        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOFactory.getSingleChatUser(chatUuid);

        if (chatUserDO == null) {
            throw new SocialParamsException("不该触发的逻辑20003");
        }

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        if (!mineUser.getUserId().equals(chatUserDO.getUserId())) {
            if (!mineUser.getType().equals(UserType.system)) {
                throw new SocialParamsException("不该触发的逻辑20004");
            }
        }

        //toDO 这里需要细想怎么个逻辑
        //需要将chatUser的未读数量更新一下
//            messageReceiveDORepository.updateMessageReceiveRead(chatUserDb, readVO.getMessageIds());
        List<SocialuniMessageReceiveDO> messageReceiveDOS = messageReceiveRepository.findByChatUserIdAndStatusAndIsReadFalse(chatUserDO.getId(), MessageStatus.enable);
//                List<MessageReceiveDO> messageReceiveDOS = new ArrayList<>();
        //把具体的每一条改为已读
        if (messageReceiveDOS.size() > 0) {
            Date curDate = new Date();
            for (SocialuniMessageReceiveDO messageReceiveDO : messageReceiveDOS) {
                messageReceiveDO.setUpdateTime(curDate);
                messageReceiveDO.setIsRead(true);
                        /*
                        toDO 暂时不需要的逻辑，这个逻辑是把msg改为已读，并且已读次数加1
                        MessageDO messageDO = messageReceiveDO.getMessage();
                        messageDO.setReadStatus(CommonStatus.read);
                        if (ChatType.groupChats.contains(chat.getType())) {
                            messageDO.setReadNum(messageDO.getReadNum() + 1);
                        }*/
            }
            messageReceiveRepository.saveAll(messageReceiveDOS);
        }


        chatUserDO.setUnreadNum(0);
        //进入chat页，列表中进入，肯定是展示的，所以不会走这里
        chatUserDO.checkFrontShowAndSetTrue();
        //目前不根据点击时间更新，只根据消息时间更新
//                chatUserDb.setUpdateTime(new Date());
        chatUserRepository.save(chatUserDO);
        return ResultRO.success();
    }

    public ResultRO<List<ChatRO>> queryHomeChat() {
        return null;
    }


    public ResultRO<List<ChatRO>> queryChatList() {
        //未登录的情况需要查询 chat，未登录的情况没有 chatUser
        //已登录的情况，查 chatUser?

        //怎么排序呢。 没登录查 chats, 登录了查 chatUsers.

        List<ChatRO> list = chatQueryDomain.getChats();
        return new ResultRO<>(list);
//        return new ResultRO<>();
    }

    public ResultRO<ChatRO> queryChat(SocialuniChatQueryQO socialuniChatQueryQO) {
        String chatIdStr = socialuniChatQueryQO.getChatId();

        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByUuidNotNull(chatIdStr);

        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        //私聊
        if (socialuniUnionIdDo.getContentType().equals(SocialuniContentType.user)) {
            Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
            Long beUserId = socialuniUnionIdDo.getSelfSysId();

            //如果用户存在查看会话
            SocialuniChatUserDO chatUserDO = SocialuniChatUserDOFactory.getOrCreateChatUsersBySingleSendMsg(mineUserId, beUserId).get(0);

            ChatRO chatRO = SocialChatROFactory.getChatROByQueryChat(chatUserDO, true);

            return ResultRO.success(chatRO);
        } else if (socialuniUnionIdDo.getContentType().equals(SocialuniContentType.chat)) {
            //则为chatId
            Long chatId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(chatIdStr);

            SocialuniChatDO chatDO = SocialuniChatDOUtil.findByUnionId(chatId);

            ChatRO chatRO = SocialChatROFactory.getNoLoginChatRO(chatDO);

            return ResultRO.success(chatRO);
        } else {
            throw new SocialParamsException("错误的会话标识");
        }
    }

    @Resource
    SocialuniChatEntity socialuniChatEntity;

    public ResultRO<Void> joinChat(String chatUid) {
        Long chatId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(chatUid);

        Long mineId = SocialuniUserUtil.getMineUserIdNotNull();

        socialuniChatEntity.getJoinOrCreateChatUser(chatId, mineId);

//        SocialuniChatUserDO chatUserDO = chatUserRepository.findFirstByChatIdAndUserId(chatId, mineId);
//
//        if (chatUserDO != null) {
//            throw new SocialSystemException("您已经加入了群聊");
//        }

        return null;
    }


    public ResultRO<ChatRO> openChat(OpenChatVO chatVO) {
        return null;
    }


    public ResultRO<?> frontDeleteChat(ChatRemoveVO chatVO) {
        return null;
    }


    public ResultRO<?> removeChat(ChatRemoveVO chatVO) {
        return null;
    }


    public ResultRO<?> closeChat(ChatRemoveVO chatVO) {
        return null;
    }

    /*public ResultRO<List<ChatRO>> queryChatList() {

        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        List<ChatRO> list1 = list.stream().map(SocialChatROFactory::getChatRO).collect(Collectors.toList());

        return ResultRO.success(list1);
    }*/

    //获取私聊的chat
    //查看对方主页时
    public ChatRO seeUserDetailGetOrCreateChat(SocialuniUserDo user, Long receiveUserId) {
//        Optional<SocialuniChatUserDO> chatUserDOOptional = chatUserRepository.findFirstByStatusAndUserIdAndBeUserId(ChatStatus.enable, user.getUnionId(), receiveUserId);
//        SocialuniChatUserDO chatUserDO;
//        //如果创建过，则获取。返回
//        if (chatUserDOOptional.isPresent()) {
//            chatUserDO = chatUserDOOptional.get();
////            Optional<ChatDO> chatDOOptional = chatRepository.findById(chatUserDO.getChatId());
//            //如果没创建过，则创建，并返回
//        } else {
//            CreateSingleChatResult chatResult = this.seeUserDetailCreateChat(user, receiveUserId);
//            chatUserDO = chatResult.getMineChatUser();
//        }
//
//        ChatRO chatUserVO = SocialChatROFactory.getChatROByQueryChat(chatUserDO, true);
//        return chatUserVO;
        return null;
    }

    //点击一个人，陌生人，点击发送消息。
    //未关注你，进来什么也不提示？提示一下，对方未关注，发送消息需要付费，改为前台显示，提示对方已关注，无需付费请刷新
    //对方已关注，提示会话未开启，点击发送时提示，会话未开启，是否确认开启会话，改为前台显示，开启时，校验对方未关注，需要付费开启，请刷新
    //已有会话，则直接进入，改为前台显示
    //登录情况下查询用户有权限的chatuser
    //详情页面，需要知道是否关注你了

    public CreateSingleChatResult seeUserDetailCreateChat(SocialuniUserDo user, Long receiveUserId) {
        SocialuniChatDO chat = new SocialuniChatDO(ChatType.single);

        //生成chat
        chat = chatRepository.savePut(chat);

        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        SocialuniChatUserDO mineChatUser = new SocialuniChatUserDO(chat, user.getUnionId(), receiveUserId);
        //创建时不改为true，详见fronshow字段，修改状态逻辑
//        mineChatUser.setFrontShow(true);

        SocialuniChatUserDO receiveChatUser = new SocialuniChatUserDO(chat, receiveUserId, user.getUnionId());

        List<SocialuniChatUserDO> chatUserDOS = Arrays.asList(mineChatUser, receiveChatUser);
        chatUserRepository.saveAll(chatUserDOS);

        //你需要自己的chat为代开起
        //需要对方的用户名，昵称。会话未开启
        return new CreateSingleChatResult(chat, mineChatUser, receiveChatUser);
    }


    //从chatUser上控制
    public ResultRO<?> quitGroupChat(SocialuniChatQueryQO chatVO) {
        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        String chatIdStr = chatVO.getChatId();
        Long chatId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(chatIdStr);

//        SocialuniChatUserDO chatUserDO = chatUserRepository.findFirstByChatIdAndUserId(chatId, mineUserId);
//
//        if (chatUserDO == null || !chatUserDO.getStatus().equals(ChatStatus.enable)) {
//            throw new SocialSystemException("未加入群聊");
//        }
//        chatUserDO.setStatus(ChatStatus.delete);
//
//        SocialuniRepositoryFacade.save(chatUserDO);

        return ResultRO.success();
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


    //创建自己和自己的私聊，用来传输东西
    public ResultRO<?> createMineSingleChat() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        return ResultRO.success();
    }

    //创建自己的群聊，自己的专属房间
    public ResultRO<?> createMineGroupChat() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        return ResultRO.success();
    }

    //加入系统的群聊
    public ResultRO<ChatRO> joinOrCreateGroupChat(SocialuniChatCreateQO socialuniChatCreateQO) {
        long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        long sysUserId = DevAccountFacade.getDevUserId();
        String chatName = socialuniChatCreateQO.getChatName();
        String chatType = socialuniChatCreateQO.getType();

        //查找是否存在此chat，根据devId查找。

        SocialuniChatDO socialuniChatDO = socialuniChatEntity.getJoinOrCreateChatUser(sysUserId, chatType, mineUserId, chatName);


        ChatRO chatRO = SocialChatROFactory.getChatROByUserLogin(socialuniChatDO, mineUserId);

        //不存在则创建
        return ResultRO.success(chatRO);
    }

}
