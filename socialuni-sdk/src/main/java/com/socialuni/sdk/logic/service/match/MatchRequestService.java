package com.socialuni.sdk.logic.service.match;

import com.socialuni.sdk.dao.DO.chat.ChatDO;
import com.socialuni.sdk.dao.DO.chat.ChatUserDO;
import com.socialuni.sdk.dao.DO.message.MessageDO;
import com.socialuni.sdk.dao.DO.message.MessageReceiveDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.constant.socialuni.ChatType;
import com.socialuni.sdk.constant.socialuni.MatchType;
import com.socialuni.sdk.constant.socialuni.MessageType;
import com.socialuni.sdk.dao.repository.ChatRepository;
import com.socialuni.sdk.dao.repository.ChatUserRepository;
import com.socialuni.sdk.dao.repository.MessageReceiveRepository;
import com.socialuni.sdk.dao.repository.MessageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

/**
 * @author qinkaiyuan
 * @date 2019-07-08 22:11
 */
@Service
public class MatchRequestService {
    @Resource
    private MessageRepository messageRepository;

    @Resource
    private ChatUserRepository chatUserRepository;
    @Resource
    private ChatRepository chatRepository;
    @Resource
    private MessageReceiveRepository messageReceiveDORepository;
    @Resource
    private EntityManager entityManager;

    @Transactional
    public void sendMatchSuccessMsgToUser(SocialuniUserDO user, SocialuniUserDO receiveUser) {
        //匹配成功
        //chat_user中为3个人。
        //一个系统用户
        //一个自己
        //一个对方
        //上来系统给对方发一个匹配成功;然后对方回复
        ChatDO chat = new ChatDO(ChatType.match);
        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        ChatUserDO mineChatUser = new ChatUserDO(chat, user.getUnionId(), receiveUser.getUnionId());
        //自己的设置为待匹配状态，需要等对方回复后才能改为正常
        mineChatUser.setStatus(MatchType.waitMatch);
        ChatUserDO receiveChatUser = new ChatUserDO(chat, receiveUser.getUnionId(), user.getUnionId());
        List<ChatUserDO> chatUserDOS = Arrays.asList(mineChatUser, receiveChatUser);
        //生成chat
        chat = chatRepository.save(chat);
        chatUserRepository.saveAll(chatUserDOS);


//        List<NotifyDO> notifies = new ArrayList<>();
        MessageDO message = new MessageDO(chat.getId(), "匹配成功，只有您能主动发起会话", user.getUnionId(), MessageType.system);
        List<MessageReceiveDO> messageReceiveDOS = new ArrayList<>();
        //给自己和对方各生成一条消息
        for (ChatUserDO chatUserDO : chatUserDOS) {
//            chatUserDO.setLastContent(message.getContent());
            chatUserDO.setUpdateTime(new Date());
            MessageReceiveDO messageReceiveDO = new MessageReceiveDO(chatUserDO, chatUserDO.getUserId(), chatUserDO.getReceiveUserId(), message);
            messageReceiveDOS.add(messageReceiveDO);
        }
        messageReceiveDORepository.saveAll(messageReceiveDOS);
        message = messageRepository.save(message);
        Optional<MessageReceiveDO> messageReceiveOptional = messageReceiveDOS.stream().filter(receiveMsg -> receiveMsg.getUserId().equals(receiveUser.getUnionId())).findFirst();
        /*if (messageReceiveOptional.isPresent()) {
            NotifyDO notifyDO = new NotifyDO(messageReceiveOptional.get());
            notifies.add(notifyDO);
            notifyRepository.saveAll(notifies);
            //保存message
            notifyService.sendNotifies(notifies);
        } else {
            QingLogger.logger.error("保存了却查询不到接受消息，msgId：{},接收人，receiveUserId:{}", message.getId(), receiveUser.getId());
        }*/
    }
}
