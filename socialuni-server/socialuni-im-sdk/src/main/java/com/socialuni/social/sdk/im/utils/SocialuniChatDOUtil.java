package com.socialuni.social.sdk.im.utils;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatDOUtil {
    static SocialuniChatRepository socialuniChatRepository;
    static SocialuniChatUserRepository socialuniChatUserRepository;

    @Resource
    public void setSocialuniChatRepository(SocialuniChatRepository socialuniChatRepository) {
        SocialuniChatDOUtil.socialuniChatRepository = socialuniChatRepository;
    }

    @Resource
    public void setSocialuniChatUserRepository(SocialuniChatUserRepository socialuniChatUserRepository) {
        SocialuniChatDOUtil.socialuniChatUserRepository = socialuniChatUserRepository;
    }

    public static SocialuniChatDO findByUnionId(Long chatId) {
        return socialuniChatRepository.findFirstByUnionId(chatId);
    }

    //支持uuid，chatUserId,chatId, 群聊应该返回chatId, 私聊应该返回UserId
    public static Long getChatId(String chatIdStr) {
        if (StringUtils.isEmpty(chatIdStr)) {
            throw new SocialBusinessException("房间信息为空");
        }

        Long mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();

        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByUuidNotNull(chatIdStr);

        Long unionId = socialuniUnionIdDo.getSelfSysId();

        if (SocialuniContentType.chat.equals(socialuniUnionIdDo.getContentType())) {
            return unionId;
        } else if (SocialuniContentType.user.equals(socialuniUnionIdDo.getContentType())) {
            SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOUtil.findByUserIdAndBeUserIdNotNull(mineUserId, unionId);
            return socialuniChatUserDO.getChatId();
        }
        throw new SocialParamsException("错误的会话信息1212");
    }
}
