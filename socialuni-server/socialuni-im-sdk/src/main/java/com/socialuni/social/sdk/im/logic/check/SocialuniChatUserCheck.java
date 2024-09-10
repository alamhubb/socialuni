package com.socialuni.social.sdk.im.logic.check;

import cn.hutool.core.util.StrUtil;
import com.socialuni.social.common.api.exception.exception.SocialNullUserException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.sdk.im.enumeration.ChatOpenType;
import com.socialuni.social.sdk.im.enumeration.ChatUserStatus;
import com.socialuni.social.sdk.im.utils.SocialuniChatDOUtil;
import com.socialuni.social.sdk.im.utils.SocialuniChatUserDOUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserCheck {

    public static void checkUserCanSeeChat(SocialuniChatDO chat, Long mineUserId) {
        if (chat == null) {
            throw new SocialParamsException("不存在的群聊");
        }
        String openType = chat.getOpenType();
        if (StrUtil.isEmpty(openType)) {
            throw new SocialSystemException("错误的群聊开放类型");
        }

        if (openType.equals(ChatOpenType.open)) {
            return;
        }

        if (mineUserId == null) {
            throw new SocialNullUserException();
        }

        //无论如何都要查询，除非user为空
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findFirstByChatIdAndUserIdAndStatusNotNull(chat.getUnionId(), mineUserId, ChatUserStatus.enable);
        //申请加入群聊相关功能，

        //如果必须加入
        if (chatUserDO == null) {
            throw new SocialParamsException("未加入群聊");
        }
    }

    public SocialuniChatUserDO checkUserInChat(Long chatId, Long mineUserId) {
        SocialuniChatDO chat = SocialuniChatDOUtil.getChat(chatId);

        if (chat == null) {
            throw new SocialParamsException("不存在的群聊");
        }

        String openType = chat.getOpenType();
        if (StrUtil.isEmpty(openType)) {
            throw new SocialSystemException("错误的群聊开放类型");
        }

        if (mineUserId == null) {
            throw new SocialParamsException("用户不能为空");
        }

        //无论如何都要查询，除非user为空
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findFirstByChatIdAndUserIdAndStatusNotNull(chatId, mineUserId, ChatUserStatus.enable);
        //申请加入群聊相关功能，

        //如果必须加入
        if (ChatOpenType.needApply.contains(openType)) {
            if (chatUserDO == null) {
                throw new SocialParamsException("未加入群聊");
            }
        }
        return chatUserDO;
    }
}
