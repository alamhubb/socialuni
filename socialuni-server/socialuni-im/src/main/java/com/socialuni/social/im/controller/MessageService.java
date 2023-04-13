package com.socialuni.social.im.controller;

import cn.hutool.core.util.NumberUtil;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.im.dao.ChatRepository;
import com.socialuni.social.im.dao.ChatUserRepository;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.im.dao.DO.SocialuniUserChatConfigDO;
import com.socialuni.social.im.dao.DO.message.MessageDO;
import com.socialuni.social.im.dao.DO.message.MessageReceiveDO;
import com.socialuni.social.im.dao.MessageReceiveRepository;
import com.socialuni.social.im.dao.MessageRepository;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.enumeration.SocialuniAddFriendStatus;
import com.socialuni.social.im.logic.entity.MessageEntity;
import com.socialuni.social.im.logic.foctory.SocialuniChatUserDOFactory;
import com.socialuni.social.im.logic.foctory.SocialuniMessageDOFactory;
import com.socialuni.social.im.logic.foctory.SocialMessageROFactory;
import com.socialuni.social.im.logic.manage.SocialuniUserChatConfigManage;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.report.sdk.utils.SocialuniTextContentUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Service
public class MessageService {
    @Resource
    private ChatRepository chatRepository;
    @Resource
    private MessageRepository messageRepository;
    @Resource
    private MessageEntity messageEntity;
    @Resource
    private MessageReceiveRepository messageReceiveRepository;

    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) {
        String receiveIdUid = msgAddVO.getReceiveId();
        String msgContent = msgAddVO.getContent();
        if (StringUtils.isEmpty(msgContent)) {
            throw new SocialBusinessException("不能发布空内容");
        }
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusEnable();

        //校验内容是否违规
        SocialuniTextContentUtil.checkTextHasViolateWords(msgContent);

        SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionByUuidNotNull(receiveIdUid);

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        //私聊
        if (socialuniUnionIdModler.getContentType().equals(SocialuniContentType.user)) {
            //为私聊相关校验
            //后端区分这个值是群聊还是私聊。
            Integer beUserId = socialuniUnionIdModler.getId();

            SocialMessageRO socialMessageRO = messageEntity.sendSingleMsg(beUserId, msgAddVO.getContent());

            return ResultRO.success(socialMessageRO);

        } else if (socialuniUnionIdModler.getContentType().equals(SocialuniContentType.chatUser)) {

            SocialuniChatDO chat = SocialuniRepositoryFacade.findById(socialuniUnionIdModler.getId(), SocialuniChatDO.class);

            //构建消息
            MessageDO message = messageRepository.save(SocialuniMessageDOFactory.createMessage(chat.getId(), msgContent, mineUser.getUserId()));

            Date curDate = new Date();
            chat.setUpdateTime(curDate);
//            chat.setLastContent(content);
            chatRepository.save(chat);

            //群聊
            SocialMessageRO messageVO = SocialMessageROFactory.getMessageRO(message, mineUser.getUserId());
            return ResultRO.success(messageVO);
        } else {
            throw new SocialParamsException("错误的会话标识");
        }
    }

}
