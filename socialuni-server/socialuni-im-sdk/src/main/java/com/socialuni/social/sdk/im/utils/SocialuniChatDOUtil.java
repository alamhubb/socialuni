package com.socialuni.social.sdk.im.utils;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
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

    public static SocialuniChatDO findFirstByUnionId(Integer chatId) {
        return socialuniChatRepository.findFirstByUnionId(chatId);
    }


    //支持uuid，chatUserId,chatId, 群聊应该返回chatId, 私聊应该返回UserId
    public static Integer getChatId(String chatIdStr) {
        if (StringUtils.isEmpty(chatIdStr)) {
            throw new SocialBusinessException("房间信息为空");
        }


        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();

        SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionByUuidAllowNull(chatIdStr);
        if (socialuniUnionIdModler != null) {
            //用户不登录也有uuid为null的
            //            if (mineUserId == null) {
//                throw new SocialParamsException("错误的会话信息1211");
//            }
            Integer unionId = socialuniUnionIdModler.getId();
            if (SocialuniContentType.chat.equals(socialuniUnionIdModler.getContentType())) {
                SocialuniChatDO socialuniChatDO = SocialuniChatDOUtil.findFirstByUnionId(unionId);
                return socialuniChatDO.getUnionId();
            } else if (SocialuniContentType.user.equals(socialuniUnionIdModler.getContentType())) {
                SocialuniChatUserDO socialuniChatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, unionId, SocialuniChatUserDO.class);
                return socialuniChatUserDO.getChatId();
            }
            throw new SocialParamsException("错误的会话信息1212");
        }
        //有可能是uuid
        Integer unionId;
        try {
            unionId = Integer.valueOf(chatIdStr);
        } catch (Exception e) {
            throw new SocialParamsException("错误的会话标识1213");
        }
        //如果用户为空，则为chatId
        if (mineUserId == null) {
            SocialuniChatDO socialuniChatDO = SocialuniChatDOUtil.findFirstByUnionId(unionId);
            if (socialuniChatDO == null) {
                throw new SocialParamsException("错误的会话标识1214");
            }
            return socialuniChatDO.getUnionId();
        } else {
            SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findById(unionId);
            if (chatUserDO == null) {
                throw new SocialParamsException("不存在会话信息1");
            }
            return chatUserDO.getChatId();
        }
    }
}
