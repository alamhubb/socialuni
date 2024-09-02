package com.socialuni.social.sdk.im.utils;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserDOUtil {
    private static SocialuniChatUserRepository chatUserRepository;

    @Resource
    public void setChatUserRepository(SocialuniChatUserRepository chatUserRepository) {
        SocialuniChatUserDOUtil.chatUserRepository = chatUserRepository;
    }

    public static SocialuniChatUserDO findById(Integer id) {
        return chatUserRepository.findFirstById(id);
    }


    public static SocialuniChatUserDO findByChatIdAndUserId(Integer chatId, Integer userId) {
        return chatUserRepository.findFirstByChatIdAndUserId(chatId, userId);
    }

    public static SocialuniChatUserDO findUserIdAndBeUserId(Integer userId, Integer beUserId) {
//        return chatUserRepository.findFirstByStatusAndUserIdAndBeUserId(SocialuniCommonStatus.enable, userId, beUserId);
        return null;
    }


    public static SocialuniChatUserDO getChatUserDONotNullByUserId(Integer mineUserId, String userIdStr) {
        if (mineUserId == null) {
            throw new SocialNotLoginException();
        }
        return null;
/*
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(unionId);


        if (chatUserDO == null) {
            throw new SocialParamsException("不存在会话信息1");
        }
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        if (!mineUser.getUserId().equals(chatUserDO.getUserId())) {
            //如果为自己或者为系统
            //为私聊相关校验
            //后端区分这个值是群聊还是私聊。
            if (!UserType.system.equals(mineUser.getType())) {
                throw new SocialParamsException("不存在的会话信息2");
            }
        }
        return chatUserDO;*/
    }

    /*public static SocialuniChatUserDO getChatUserDO(String uuid) {
        SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionByUuidNotNull(uuid);

        Integer unionId = socialuniUnionIdModler.getId();

        if (SocialuniContentType.chat.equals(socialuniUnionIdModler.getContentType())) {
            SocialuniChatDO socialuniChatDO = SocialuniChatDOUtil.findFirstByUnionId(unionId);

            SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId()

            SocialuniChatUserDOUtil.findById()

            return socialuniChatDO.getUnionId();
        } else if (SocialuniContentType.user.equals(socialuniUnionIdModler.getContentType())) {
            SocialuniChatUserDO socialuniChatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, unionId, SocialuniChatUserDO.class);
            return socialuniChatUserDO.getChatId();
        }
        throw new SocialParamsException("错误的会话信息1212");
    }*/
}
