package com.socialuni.social.sdk.im.logic.service;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.repository.SocialuniCommonRepository;
import com.socialuni.social.common.sdk.utils.ErrorLogUtil;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.ChatUserRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageReceiveRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageRepository;
import com.socialuni.social.sdk.im.enumeration.*;
import com.socialuni.social.sdk.im.logic.entity.SocialuniMessageEntity;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatUserManage;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
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
    @Resource
    SocialuniChatRepository socialuniChatRepository;

    @Resource
    SocialuniChatUserManage socialuniChatUserManage;


    @Transactional
    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) {
        String chatId = msgAddVO.getReceiveId();
        String msgContent = msgAddVO.getContent();
        String msgType = msgAddVO.getType();
        if (StringUtils.isEmpty(msgType)) {
            msgType = MessageContentType.text;
        }
        if (StringUtils.isEmpty(msgContent)) {
            throw new SocialBusinessException("不能发布空内容");
        }
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusEnable();

        //校验内容是否违规
        SocialuniTextContentUtil.checkTextHasViolateWords(msgContent);

        SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionByUuidAllowNull(chatId);


        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        Integer unionId = null;

        if (socialuniUnionIdModler == null) {
            try {
                unionId = Integer.valueOf(chatId);
            } catch (Exception e) {
                throw new SocialParamsException("错误的会话标识");
            }
            SocialuniChatUserDO socialuniChatUserDO = SocialuniRepositoryFacade.findById(unionId, SocialuniChatUserDO.class);
            if (socialuniChatUserDO == null) {
                throw new SocialParamsException("不存在会话信息1");
            }
            SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

            if (!mineUser.getUserId().equals(socialuniChatUserDO.getUserId())) {
                //如果为自己或者为系统
                //为私聊相关校验
                //后端区分这个值是群聊还是私聊。
                if (!UserType.system.equals(mineUser.getType())) {
                    throw new SocialParamsException("不存在的会话信息2");
                }
                mineUser = SocialuniUserUtil.getUserNotNull(socialuniChatUserDO.getUserId());
            }

            SocialMessageRO socialMessageRO = messageEntity.sendSingleMsg(mineUser, socialuniChatUserDO.getBeUserId(), msgAddVO.getContent(), msgType);

            return ResultRO.success(socialMessageRO);
            //则为chatUserId
        } else {
            unionId = socialuniUnionIdModler.getId();

            String idContentType = socialuniUnionIdModler.getContentType();
            //旧版本
            if (idContentType.equals(SocialuniContentType.user)) {
                //为私聊相关校验
                //后端区分这个值是群聊还是私聊。

                SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

                SocialMessageRO socialMessageRO = messageEntity.sendSingleMsg(mineUser, unionId, msgAddVO.getContent(), msgType);

//                ErrorLogUtil.error("不应该存在用户id的会话消息");

                return ResultRO.success(socialMessageRO);
            } else if (idContentType.equals(SocialuniContentType.chat)) {


                //获取chat的类型，是不是私聊


                //群聊
                SocialMessageRO socialMessageRO = messageEntity.sendGroupMessage(unionId, msgAddVO.getContent(), msgType);

                return ResultRO.success(socialMessageRO);

            } else {
                throw new SocialParamsException("错误的会话标识");
            }
        }
    }
}
