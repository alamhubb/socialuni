package com.socialuni.social.common.sdk.logic.service.match;

import com.socialuni.social.common.sdk.constant.socialuni.MatchType;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.logic.foctory.SocialuniMessageDOFactory;
import com.socialuni.social.im.enumeration.MessageType;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.im.dao.DO.message.SocialuniMessageReceiveDO;
import com.socialuni.social.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.im.dao.repository.ChatUserRepository;
import com.socialuni.social.im.dao.repository.SocialuniMessageReceiveRepository;
import com.socialuni.social.im.dao.repository.SocialuniMessageRepository;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    private SocialuniMessageRepository messageRepository;

    @Resource
    private ChatUserRepository chatUserRepository;
    @Resource
    private SocialuniChatRepository chatRepository;
    @Resource
    private SocialuniMessageReceiveRepository messageReceiveDORepository;
    @Resource
    private EntityManager entityManager;

    @Transactional
    public void sendMatchSuccessMsgToUser(SocialuniUserDo user, SocialuniUserDo receiveUser) {
        //匹配成功
        //chat_user中为3个人。
        //一个系统用户
        //一个自己
        //一个对方
        //上来系统给对方发一个匹配成功;然后对方回复
        SocialuniChatDO chat = new SocialuniChatDO(ChatType.match);
        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        SocialuniChatUserDO mineChatUser = new SocialuniChatUserDO(chat, user.getUnionId(), receiveUser.getUnionId());
        //自己的设置为待匹配状态，需要等对方回复后才能改为正常
        mineChatUser.setStatus(MatchType.waitMatch);
        SocialuniChatUserDO receiveChatUser = new SocialuniChatUserDO(chat, receiveUser.getUnionId(), user.getUnionId());
        List<SocialuniChatUserDO> chatUserDOS = Arrays.asList(mineChatUser, receiveChatUser);
        //生成chat
        chat = chatRepository.save(chat);
        chatUserRepository.saveAll(chatUserDOS);


//        List<NotifyDO> notifies = new ArrayList<>();
        SocialuniMessageDO message = SocialuniMessageDOFactory.createMessage(chat.getUnionId(), "匹配成功，只有您能主动发起会话", user.getUnionId(), MessageType.system);
        List<SocialuniMessageReceiveDO> messageReceiveDOS = new ArrayList<>();
        //给自己和对方各生成一条消息
        for (SocialuniChatUserDO chatUserDO : chatUserDOS) {
//            chatUserDO.setLastContent(message.getContent());
            chatUserDO.setUpdateTime(new Date());
            SocialuniMessageReceiveDO messageReceiveDO = new SocialuniMessageReceiveDO(chatUserDO, message.getUserId(), message.getUnionId());
            messageReceiveDOS.add(messageReceiveDO);
        }
        messageReceiveDORepository.saveAll(messageReceiveDOS);
        message = messageRepository.save(message);
        Optional<SocialuniMessageReceiveDO> messageReceiveOptional = messageReceiveDOS.stream().filter(receiveMsg -> receiveMsg.getUserId().equals(receiveUser.getUnionId())).findFirst();
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
