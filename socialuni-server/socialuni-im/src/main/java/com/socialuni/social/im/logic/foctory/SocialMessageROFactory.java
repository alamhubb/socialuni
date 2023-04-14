package com.socialuni.social.im.logic.foctory;


import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.im.enumeration.MessageReadStatus;
import com.socialuni.social.im.enumeration.MessageStatus;
import com.socialuni.social.im.dao.DO.message.MessageDO;
import com.socialuni.social.im.dao.DO.message.MessageReceiveDO;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-14 17:24
 */
@Data
@Component
public class SocialMessageROFactory {

    public static SocialMessageRO getMessageRO(MessageDO messageDO, Integer lookMessageUserId) {
        SocialMessageRO messageRO = new SocialMessageRO();

        SocialuniUserDo userDO = SocialuniUserUtil.getAndCheckUserNotNull(messageDO.getUserId());
        SocialuniUserRO messageUser = SocialuniUserROFactory.getUserRO(userDO, SocialuniUserUtil.getMineUserNotNull());
        boolean isMine = messageDO.getUserId().equals(lookMessageUserId);

        messageRO.setId(messageDO.getUnionId());
        messageRO.setContent(messageDO.getContent());
        messageRO.setCreateTime(messageDO.getCreateTime());
        messageRO.setUser(messageUser);
        messageRO.setIsMine(isMine);

        messageRO.setReadStatus(MessageReadStatus.sended);
        messageRO.setReadNum(messageDO.getReadNum());
        messageRO.setType(messageDO.getType());
        return messageRO;
    }

    //websocket推新消息时设置为未读
    public SocialMessageRO getMessageRO(MessageDO messageDO, boolean readFlag, SocialuniUserDo user) {
        SocialMessageRO socialMessageRO = SocialMessageROFactory.getMessageRO(messageDO, user.getUnionId());
        socialMessageRO.setIsRead(false);
        return socialMessageRO;
    }

    /*
    //默认发送的自己的消息，可是有下面那个根据id判断的了就不需要这个了吧
    public MessageVO(MessageDO messageDO, String mineFlag) {
        this(messageDO);
        this.isMine = true;
    }*/

    /*public MessageVO(MessageDO messageDO, Integer userId, UserDO user) {
        this(messageDO, user.getUserId());
    }*/

    public static SocialMessageRO getMessageRO(MessageReceiveDO messageReceive) {

        MessageDO messageDO = SocialuniRepositoryFacade.findById(messageReceive.getMessageId(),MessageDO.class);

        SocialMessageRO messageRO = SocialMessageROFactory.getMessageRO(messageDO, messageReceive.getBeUserId());
        //涉及到举报，不知道是msgid还是msguserid，所以暂时取消，统一使用msgid，删除和举报
//        this.id = messageReceive.getId();
        if (messageReceive.getIsMine()) {
            //自己发的消息就去msg上的发送状态
            messageRO.setReadStatus(messageRO.getReadStatus());
        } else {
            //否则就取自己的阅读状态
            messageRO.setIsRead(messageReceive.getIsRead());
        }
        return messageRO;
        //待定不知道这个是干嘛的
//            this.updateTIme = messageDO.getUpdateTime();
    }

    public static List<SocialMessageRO> messageReceiveDOToVOS(List<MessageReceiveDO> messageDOS) {
        //翻转数组,因为查出来的是倒序的
        List<SocialMessageRO> messageVOS = ListConvertUtil.toList(SocialMessageROFactory::getMessageRO, messageDOS);
        Collections.reverse(messageVOS);
        return messageVOS;
    }

    //未登录的消息，消息发给谁的，应该是谁的id，查看消息的人的id，因为消息存在推的情况
    public static List<SocialMessageRO> messageDOToVOS(List<MessageDO> messageDOS, Integer lookMessageUserId) {
        //翻转数组,因为查出来的是倒序的
        List<SocialMessageRO> messageVOS = ListConvertUtil.toList(SocialMessageROFactory::getMessageRO, messageDOS, lookMessageUserId);
        Collections.reverse(messageVOS);
        return messageVOS;
    }

    /*
    //根据chatUser判断了，不需要这个了
    public static List<SocialMessageRO> messageDOToVOS(List<MessageDO> messageDOS, Integer userId) {
        //翻转数组,因为查出来的是倒序的 (ChatUserDO chatUserDO) -> new ChatVO(chatUserDO, true)
        List<SocialMessageRO> messageVOS = messageDOS.stream().map((MessageDO msg) -> new MessageVO(msg, userId)).collect(Collectors.toList());
        Collections.reverse(messageVOS);
        return messageVOS;
    }*/



    /*public MessageVO(MessageDO messageDO) {
        this.id = messageDO.getId();
        this.content = messageDO.getContent();
        this.createTime = messageDO.getCreateTime().getTime();
        //消息的用户
        this.user = new MessageUserVO(messageDO.getUserId());
        this.isMine = false;
        this.readStatus = CommonStatus.sended;
        this.isRead = true;
        this.readNum = messageDO.getReadNum();
        this.type = messageDO.getType();
    }

    //websocket推新消息时设置为未读
    public MessageVO(MessageDO messageDO, boolean readFlag) {
        this(messageDO);
        this.isRead = false;
    }

    *//*
    //默认发送的自己的消息，可是有下面那个根据id判断的了就不需要这个了吧
    public MessageVO(MessageDO messageDO, String mineFlag) {
        this(messageDO);
        this.isMine = true;
    }*//*

    public MessageVO(MessageDO messageDO, Integer userId) {
        this(messageDO);
        //不为空判断
        if (!ObjectUtils.isEmpty(userId)) {
            this.isMine = this.user.getId().equals(userId);
        }
    }

    public MessageVO(MessageReceiveDO messageReceive) {
        this(messageReceive.getMessage());
        //涉及到举报，不知道是msgid还是msguserid，所以暂时取消，统一使用msgid，删除和举报
//        this.id = messageReceive.getId();
        this.isMine = messageReceive.getIsMine();
        if (this.isMine) {
            //自己发的消息就去msg上的发送状态
            this.readStatus = messageReceive.getMessage().getReadStatus();
        } else {
            //否则就取自己的阅读状态
            this.isRead = messageReceive.getIsRead();
        }
        //待定不知道这个是干嘛的
//            this.updateTIme = messageDO.getUpdateTime();
    }

    public static List<MessageVO> messageReceiveDOToVOS(List<MessageReceiveDO> messageDOS) {
        //翻转数组,因为查出来的是倒序的
        List<MessageVO> messageVOS = messageDOS.stream().map(MessageVO::new).collect(Collectors.toList());
        Collections.reverse(messageVOS);
        return messageVOS;
    }

    //未登录的消息
    public static List<MessageVO> messageDOToVOS(List<MessageDO> messageDOS, Integer userId) {
        //翻转数组,因为查出来的是倒序的
        List<MessageVO> messageVOS = messageDOS.stream().map(messageDO -> new MessageVO(messageDO, userId)).collect(Collectors.toList());
        Collections.reverse(messageVOS);
        return messageVOS;
    }*/

    /*
    //根据chatUser判断了，不需要这个了
    public static List<MessageVO> messageDOToVOS(List<MessageDO> messageDOS, Integer userId) {
        //翻转数组,因为查出来的是倒序的 (ChatUserDO chatUserDO) -> new ChatVO(chatUserDO, true)
        List<MessageVO> messageVOS = messageDOS.stream().map((MessageDO msg) -> new MessageVO(msg, userId)).collect(Collectors.toList());
        Collections.reverse(messageVOS);
        return messageVOS;
    }*/
}
