package com.socialuni.social.sdk.im.logic.domain;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.sdk.im.enumeration.MessageContentType;
import com.socialuni.social.sdk.im.logic.entity.SocialuniMessageEntity;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniMsgDomain {
    @Resource
    private SocialuniMessageEntity messageEntity;


    public SocialMessageRO sendMsg(MessageAddVO msgAddVO) {
        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByUuidNotNull(msgAddVO.getChatId());

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


        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        String idContentType = socialuniUnionIdDo.getContentType();


        Long unionId = socialuniUnionIdDo.getSelfSysId();


        //旧版本
        if (idContentType.equals(SocialuniContentType.user)) {
            //为私聊相关校验
            //后端区分这个值是群聊还是私聊。

            SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

            SocialMessageRO socialMessageRO = messageEntity.sendSingleMsg(mineUser, unionId, msgAddVO.getContent(), msgType);

//                ErrorLogUtil.error("不应该存在用户id的会话消息");

            return socialMessageRO;
        } else if (idContentType.equals(SocialuniContentType.chat)) {
            //获取chat的类型，是不是私聊
            //群聊
            SocialMessageRO socialMessageRO = messageEntity.sendGroupMessage(unionId, msgAddVO.getContent(), msgType);

            System.out.println("fanhuile1111");


            return socialMessageRO;

        } else {
            throw new SocialParamsException("错误的会话标识");
        }
    }
}
