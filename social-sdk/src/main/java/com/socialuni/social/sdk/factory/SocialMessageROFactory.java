package com.socialuni.social.sdk.factory;


import com.socialuni.constant.MessageStatus;
import com.socialuni.social.sdk.factory.user.base.SocialUserROFactory;
import com.socialuni.social.entity.model.DO.message.MessageDO;
import com.socialuni.social.entity.model.DO.message.MessageReceiveDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.RO.message.message.SocialMessageRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserRO;
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

        UserDO userDO = SocialUserUtil.get(messageDO.getUserId());
        SocialUserRO messageUser = SocialUserROFactory.getUserRO(userDO);
        boolean isMine = messageDO.getUserId().equals(lookMessageUserId);

        messageRO.setId(messageDO.getId());
        messageRO.setContent(messageDO.getContent());
        messageRO.setCreateTime(messageDO.getCreateTime());
        messageRO.setUser(messageUser);
        messageRO.setIsMine(isMine);

        messageRO.setReadStatus(MessageStatus.sended);
        messageRO.setReadNum(messageDO.getReadNum());
        messageRO.setType(messageDO.getType());
        return messageRO;
    }

    //websocket推新消息时设置为未读
    public SocialMessageRO getMessageRO(MessageDO messageDO, boolean readFlag, UserDO user) {
        SocialMessageRO socialMessageRO = SocialMessageROFactory.getMessageRO(messageDO, user.getId());
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
        SocialMessageRO messageRO = SocialMessageROFactory.getMessageRO(messageReceive.getMessage(), messageReceive.getReceiveUserId());
        //涉及到举报，不知道是msgid还是msguserid，所以暂时取消，统一使用msgid，删除和举报
//        this.id = messageReceive.getId();
        if (messageReceive.getIsMine()) {
            //自己发的消息就去msg上的发送状态
            messageRO.setReadStatus(messageReceive.getMessage().getReadStatus());
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
}
