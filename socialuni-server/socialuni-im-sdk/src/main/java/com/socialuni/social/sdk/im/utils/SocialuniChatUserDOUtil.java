package com.socialuni.social.sdk.im.utils;

import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserDOUtil {
    private static SocialuniChatUserRepository chatUserRepository;

    @Resource
    public void setChatUserRepository(SocialuniChatUserRepository chatUserRepository) {
        SocialuniChatUserDOUtil.chatUserRepository = chatUserRepository;
    }

    public static SocialuniChatUserDO findByChatIdAndUserId(Long chatId, Long userId) {
        Integer chatUserId = chatUserRepository.findFirstByChatIdAndUserId(chatId, userId);
        if (chatUserId == null) {
            return null;
        }
        return SocialuniChatUserDOUtil.findById(chatUserId);
    }

    public static SocialuniChatUserDO findFirstByChatIdAndUserIdAndStatus(Long chatId, Long userId, String status) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(chatId, userId);
        if (status.equals(chatUserDO.getStatus())) {
            return chatUserDO;
        }
        return null;
    }

    public static SocialuniChatUserDO findByUserIdAndBeUserId(Long userId, Long beUserId) {
        Integer chatUserId = chatUserRepository.findFirstByUserIdAndBeUserId(userId, beUserId);
        if (chatUserId == null) {
            return null;
        }
        return SocialuniChatUserDOUtil.findById(chatUserId);
    }

    public static SocialuniChatUserDO findByUserIdAndBeUserIdNotNull(Long userId, Long beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByUserIdAndBeUserId(userId, beUserId);
        if (chatUserDO == null) {
            throw new SocialParamsException("会话信息错误100323");
        }
        return chatUserDO;
    }

    public static SocialuniChatUserDO findById(Integer id) {
        return chatUserRepository.findFirstById(id);
    }

    public static SocialuniChatUserDO save(SocialuniChatUserDO socialuniChatUserDO) {
        return chatUserRepository.savePut(socialuniChatUserDO);
    }
}
