package com.socialuni.social.sdk.im.utils;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatDOUtil {
    static SocialuniChatRepository socialuniChatRepository;

    @Resource
    public void setSocialuniChatRepository(SocialuniChatRepository socialuniChatRepository) {
        SocialuniChatDOUtil.socialuniChatRepository = socialuniChatRepository;
    }

    public static SocialuniChatDO save(SocialuniChatDO chatDO) {
        return socialuniChatRepository.savePut(chatDO);
    }

    public static SocialuniChatDO getChat(Long chatId) {
        return socialuniChatRepository.findFirstByUnionId(chatId);
    }

    public static SocialuniChatDO getChat(String chatIdStr) {
        Long chatId = getChatId(chatIdStr);
        return getChat(chatId);
    }

    public static SocialuniChatDO getChatAndStatus(String chatIdStr, String status) {
        Long chatId = getChatId(chatIdStr);
        SocialuniChatDO chatDO = getChat(chatId);
        if (chatDO == null) {
            return null;
        }
        if (!status.equals(chatDO.getStatus())) {
            return null;
        }
        return chatDO;
    }

    public static SocialuniChatDO getChatAndStatusNotNull(String chatIdStr, String status) {
        SocialuniChatDO chatDO = getChatAndStatus(chatIdStr, status);
        if (chatDO == null) {
            throw new SocialParamsException("会话不存在");
        }
        return chatDO;
    }


    public static Long getChatIdAllowNull(String chatIdStr) {
        if (StringUtils.isEmpty(chatIdStr)) {
            throw new SocialBusinessException("房间信息为空");
        }

        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByCenterUuidAllNull(chatIdStr);
        if (socialuniUnionIdDo == null) {
            return null;
        }

        Long unionId = socialuniUnionIdDo.getSelfSysId();

        if (SocialuniContentType.chat.equals(socialuniUnionIdDo.getContentType())) {
            return unionId;
        } else if (SocialuniContentType.user.equals(socialuniUnionIdDo.getContentType())) {
            Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
            SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOUtil.findByUserIdAndBeUserIdNotNull(mineUserId, unionId);
            return socialuniChatUserDO.getChatId();
        }
        throw new SocialParamsException("错误的会话信息1212");
    }

    //支持uuid，chatUserId,chatId, 群聊应该返回chatId, 私聊应该返回UserId
    public static Long getChatId(String chatIdStr) {
        if (StringUtils.isEmpty(chatIdStr)) {
            throw new SocialBusinessException("房间信息为空");
        }

        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByUuidNotNull(chatIdStr);

        Long unionId = socialuniUnionIdDo.getSelfSysId();

        if (SocialuniContentType.chat.equals(socialuniUnionIdDo.getContentType())) {
            return unionId;
        } else if (SocialuniContentType.user.equals(socialuniUnionIdDo.getContentType())) {
            Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
            SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOUtil.findByUserIdAndBeUserIdNotNull(mineUserId, unionId);
            return socialuniChatUserDO.getChatId();
        }
        throw new SocialParamsException("错误的会话信息1212");
    }
}
