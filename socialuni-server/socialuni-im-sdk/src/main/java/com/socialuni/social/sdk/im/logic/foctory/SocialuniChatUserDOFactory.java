package com.socialuni.social.sdk.im.logic.foctory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.sdk.constant.SocialuniSysRoleId;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.utils.SocialuniChatUserDOUtil;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import java.util.Arrays;
import java.util.List;

public class SocialuniChatUserDOFactory {


    //    有用户就是chatUserId，没有就是chatId,chatId是uuid，chatUserid没有uuid
    public static SocialuniChatUserDO getSingleChatUser(String chatId) {
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
    }

    public static Integer getChatId(String chatId) {
//        SocialuniChatUserDO socialuniChatUserDO = getSingleChatUser(chatId);
//        if (socialuniChatUserDO == null) {
//            SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionByUuidNotNull(chatId);
//            return socialuniUnionIdModler.getId();
//        }
//        return socialuniChatUserDO.getChatId();
        return null;
    }

    public static SocialuniChatUserDO getChatUserDO(Long userId, Long beUserId) {
        return SocialuniChatUserDOUtil.findByChatIdAndUserId(userId, beUserId);
    }

    public static List<SocialuniChatUserDO> getOrCreateChatUsersBySingleSendMsg(Long userId, Long beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(userId, beUserId);
        SocialuniChatUserDO beChatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(beUserId, userId);
        if (chatUserDO == null) {
            if (beChatUserDO != null) {
                throw new SocialSystemException("异常逻辑，非正常存在的会话");
            }
            return createSocialuniChatUserDOS(userId, beUserId);
        }
        if (!userId.equals(beUserId)) {
            return Arrays.asList(chatUserDO, beChatUserDO);
        }
        return Arrays.asList(chatUserDO);
    }

    public static List<SocialuniChatUserDO> createSocialuniChatUserDOS(Long userId, Long beUserId) {
        SocialuniChatUserDO chatUserDO;
        SocialuniChatUserDO beChatUserDO;
        //会话不存在则创建
        SocialuniChatDO chatDO = SocialuniChatDOFactory.getChatIdByCreateByDomainType();
        //会话不存在则创建
        chatUserDO = new SocialuniChatUserDO(chatDO, userId, beUserId);
        chatUserDO = SocialuniChatUserDOUtil.save(chatUserDO);

        //存在自己给自己发消息的情况
        if (!userId.equals(beUserId)) {
            beChatUserDO = new SocialuniChatUserDO(chatDO, beUserId, userId);
            beChatUserDO = SocialuniRepositoryFacade.save(beChatUserDO);
            return Arrays.asList(chatUserDO, beChatUserDO);
        }
        return Arrays.asList(chatUserDO, chatUserDO);
    }

    public static SocialuniChatUserDO getOrCreateChatUserBySingleReceiveMsg(SocialuniChatDO chatDO, Long userId, Long beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(userId, beUserId);
        if (chatUserDO == null) {
            //会话不存在则创建
            chatUserDO = new SocialuniChatUserDO(chatDO, userId, beUserId);

            chatUserDO = SocialuniChatUserDOUtil.save(chatUserDO);
        }
        return chatUserDO;
    }

    public static SocialuniChatUserDO createGroupChatUser(SocialuniChatDO chatDO, Long userId) {
        //会话不存在则创建
        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO(chatDO, userId);

        if (chatUserDO.getType().equals(ChatType.system_group)) {
            Long systemUserId = SocialuniUserUtil.getSystemUserIdNotNull();
            if (userId.equals(systemUserId)) {
                chatUserDO.setChatRoleId(SocialuniSysRoleId.owner);
            }
        }

        chatUserDO = SocialuniChatUserDOUtil.save(chatUserDO);
        return chatUserDO;
    }

    public static SocialuniChatUserDO createUserPersonalChatUser(SocialuniChatDO chatDO, Long userId) {
        //会话不存在则创建
        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO(chatDO, userId);

        chatUserDO.setChatRoleId(SocialuniSysRoleId.owner);

        chatUserDO = SocialuniChatUserDOUtil.save(chatUserDO);
        return chatUserDO;
    }
}
