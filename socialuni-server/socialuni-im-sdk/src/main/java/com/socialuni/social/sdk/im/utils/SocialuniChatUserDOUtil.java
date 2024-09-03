package com.socialuni.social.sdk.im.utils;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.sdk.im.constant.ChatUserRedisKey;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserDOUtil {
    private static SocialuniChatUserRepository chatUserRepository;

    @Resource
    public void setChatUserRepository(SocialuniChatUserRepository chatUserRepository) {
        SocialuniChatUserDOUtil.chatUserRepository = chatUserRepository;
    }

    public static SocialuniChatUserDO findByChatIdAndUserId(Integer chatId, Integer userId) {
        Integer chatUserId = chatUserRepository.findFirstByChatIdAndUserId(chatId, userId);
        if (chatUserId == null) {
            return null;
        }
        return SocialuniChatUserDOUtil.findById(chatUserId);
    }

    public static SocialuniChatUserDO findFirstByChatIdAndUserIdAndStatus(Integer chatId, Integer userId, String status) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(chatId, userId);
        if (status.equals(chatUserDO.getStatus())) {
            return chatUserDO;
        }
        return null;
    }

    public static SocialuniChatUserDO findByUserIdAndBeUserId(Integer userId, Integer beUserId) {
        Integer chatUserId = chatUserRepository.findFirstByUserIdAndBeUserId(userId, beUserId);
        if (chatUserId == null) {
            return null;
        }
        return SocialuniChatUserDOUtil.findById(chatUserId);
    }

    public static SocialuniChatUserDO findByUserIdAndBeUserIdNotNull(Integer userId, Integer beUserId) {
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
