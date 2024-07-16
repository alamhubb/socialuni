package com.socialuni.social.sdk.im.logic.check;

import cn.hutool.core.util.StrUtil;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.sdk.im.enumeration.ChatOpenType;
import com.socialuni.social.sdk.im.enumeration.ChatUserStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserCheck {
    @Resource
    private SocialuniChatUserRepository chatUserRepository;

    public SocialuniChatUserDO checkUserInChat(Integer chatId, Integer sendUserId) {
        SocialuniChatDO chat = SocialuniRepositoryFacade.findByUnionId(chatId, SocialuniChatDO.class);

        if (chat == null) {
            throw new SocialParamsException("不存在的群聊");
        }

        String openType = chat.getOpenType();
        if (StrUtil.isEmpty(openType)) {
            throw new SocialSystemException("错误的群聊开放类型");
        }

        if (sendUserId == null) {
            throw new SocialParamsException("用户不能为空");
        }

        //无论如何都要查询，除非user为空
        SocialuniChatUserDO chatUserDO = chatUserRepository.findFirstByChatIdAndUserIdAndStatus(chatId, sendUserId, ChatUserStatus.enable);

        //如果必须加入
        if (ChatOpenType.needApply.contains(openType)) {
            if (chatUserDO == null) {
                throw new SocialParamsException("未加入群聊");
            }
        }
        return chatUserDO;
    }
}
