package com.socialuni.social.sdk.im.logic.service;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageReceiveRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageRepository;
import com.socialuni.social.sdk.im.enumeration.MessageType;
import com.socialuni.social.sdk.im.logic.entity.SocialuniMessageEntity;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Service
public class SocialuniMessageService {
    @Resource
    private SocialuniChatRepository chatRepository;
    @Resource
    private SocialuniMessageRepository messageRepository;
    @Resource
    private SocialuniMessageEntity messageEntity;
    @Resource
    private SocialuniMessageReceiveRepository messageReceiveRepository;

    @Transactional
    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) {
        String receiveIdUid = msgAddVO.getReceiveId();
        String msgContent = msgAddVO.getContent();
        String msgType = msgAddVO.getType();
        if (StringUtils.isEmpty(msgType)) {
            msgType = MessageType.simple;
        }
        if (StringUtils.isEmpty(msgContent) && msgType.equals(MessageType.simple)) {
            throw new SocialBusinessException("不能发布空内容");
        }
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusEnable();

        //校验内容是否违规
        SocialuniTextContentUtil.checkTextHasViolateWords(msgContent);

        SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionByUuidNotNull(receiveIdUid);

        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        Integer unionId = socialuniUnionIdModler.getId();
        //私聊
        if (socialuniUnionIdModler.getContentType().equals(SocialuniContentType.user)) {
            //为私聊相关校验
            //后端区分这个值是群聊还是私聊。

            SocialMessageRO socialMessageRO = messageEntity.sendSingleMsg(unionId, msgAddVO.getContent(), msgAddVO.getType());

            return ResultRO.success(socialMessageRO);

        } else if (socialuniUnionIdModler.getContentType().equals(SocialuniContentType.chat)) {
            //群聊


            SocialMessageRO socialMessageRO = messageEntity.sendGroupMessage(unionId, msgAddVO.getContent());

            return ResultRO.success(socialMessageRO);

        } else {
            throw new SocialParamsException("错误的会话标识");
        }
    }

}
