package com.socialuni.social.sdk.im.utils;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatUserManage;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserDOUtil {
    private static SocialuniChatUserRepository chatUserRepository;

    @Resource
    public void setChatUserRepository(SocialuniChatUserRepository chatUserRepository) {
        SocialuniChatUserDOUtil.chatUserRepository = chatUserRepository;
    }

    //    有用户就是chatUserId，没有就是chatId,chatId是uuid，chatUserid没有uuid
   /* public static SocialuniChatUserDO getSingleChatUser(String chatId) {
        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByUuidAllowNull(chatId);

        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功
        if (socialuniUnionIdDo == null) {
            int unionId;
            try {
                unionId = Integer.parseInt(chatId);
            } catch (Exception e) {
                throw new SocialParamsException("错误的会话标识10011");
            }
            SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOUtil.get(unionId);
            if (socialuniChatUserDO == null) {
                throw new SocialParamsException("不存在会话信息10012");
            }
            return socialuniChatUserDO;
            //则为chatUserId
        } else {
            //旧版本
            String contentType = socialuniUnionIdDo.getContentType();
            //私聊
            if (contentType.equals(SocialuniContentType.user)) {
                Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
                Long beUserId = socialuniUnionIdDo.getSelfSysId();

                SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(mineUserId, beUserId);

                return chatUserDO;
            } else if (contentType.equals(SocialuniContentType.chat)) {
                return null;
            }
            throw new SocialParamsException("错误的会话标识10013");
        }
    }*/

    public static SocialuniChatUserDO getChatUserNotNull(String chatIdStr) {
        SocialuniChatUserDO socialuniChatUserDO = getChatUserAllowNull(chatIdStr);
        if (socialuniChatUserDO == null) {
            throw new SocialParamsException("会话信息为空异常326002");
        }

        return socialuniChatUserDO;
    }

    public static SocialuniChatUserDO getChatUserAllowNull(String chatIdStr) {
        Long mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();
        if (ObjectUtils.isEmpty(mineUserId)) {
            return null;
        }
        Long chatId = SocialuniChatDOUtil.getChatIdAllowNull(chatIdStr);
        if (chatId == null) {
            return null;
        }

        SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(chatId, mineUserId);

        return socialuniChatUserDO;
    }

    public static SocialuniChatUserDO findByChatIdAndUserId(Long chatId, Long userId) {
        Integer chatUserId = chatUserRepository.findFirstByChatIdAndUserId(chatId, userId);
        if (chatUserId == null) {
            return null;
        }
        return SocialuniChatUserDOUtil.get(chatUserId);
    }

    public static SocialuniChatUserManage socialuniChatUserManage;

    @Resource
    public void setSocialuniChatUserManage(SocialuniChatUserManage socialuniChatUserManage) {
        SocialuniChatUserDOUtil.socialuniChatUserManage = socialuniChatUserManage;
    }


    public static SocialuniChatUserDO getOrCreate(SocialuniChatDO chatDO, Long userId) {
        return socialuniChatUserManage.joinOrCreateChatUser(chatDO, userId);
    }

    public static SocialuniChatUserDO findFirstByChatIdAndUserIdAndStatusNotNull(Long chatId, Long userId, String status) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(chatId, userId);
        if (chatUserDO == null) {
            return null;
        }
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
        return SocialuniChatUserDOUtil.get(chatUserId);
    }

    public static SocialuniChatUserDO findByUserIdAndBeUserIdNotNull(Long userId, Long beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByUserIdAndBeUserId(userId, beUserId);
        if (chatUserDO == null) {
            throw new SocialParamsException("会话信息错误100323");
        }
        return chatUserDO;
    }

    public static SocialuniChatUserDO get(Integer id) {
        return chatUserRepository.findFirstById(id);
    }

    public static SocialuniChatUserDO save(SocialuniChatUserDO socialuniChatUserDO) {
        return chatUserRepository.savePut(socialuniChatUserDO);
    }
}
