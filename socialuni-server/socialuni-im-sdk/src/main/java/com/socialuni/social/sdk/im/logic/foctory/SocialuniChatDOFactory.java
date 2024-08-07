package com.socialuni.social.sdk.im.logic.foctory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.sdk.im.constant.SocialuniChatDomainType;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.enumeration.ChatOpenType;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class SocialuniChatDOFactory {

    public static SocialuniChatDO getChatIdByCreateByDomainType(String chatDomainType) {
        SocialuniChatDO chatDO = new SocialuniChatDO();

        Integer uid = SocialuniUnionIdFacede.createChatUnionId();
        chatDO.setUnionId(uid);
        chatDO.setContentType(SocialuniContentType.chat);
        chatDO.setType(ChatType.single);
        chatDO.setDomainType(chatDomainType);

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        chatDO.setUserId(mineUserId);
        chatDO = SocialuniRepositoryFacade.save(chatDO);
        return chatDO;
    }

    public static SocialuniChatDO getChatIdByCreateByDomainType() {
        //先把左右都改成like
        return getChatIdByCreateByDomainType(SocialuniChatDomainType.like);
//        return getChatIdByCreateByType(ChatType.single);
    }

    public static SocialuniChatDO createSystemGroupChat(String chatName) {
        return SocialuniChatDOFactory.createGroupChat(chatName, null, ChatType.system_group, SocialuniSystemConst.getTagDefaultAvatar());
    }

    public static SocialuniChatDO createCircleGroupChat(String circleName, String avatar) {
        return SocialuniChatDOFactory.createGroupChat(circleName, SocialuniUserUtil.getSystemUserIdNotNull(), ChatType.circleGroup, avatar);
    }

    public static SocialuniChatDO createUserPersonalChat(SocialuniUserDo userDo) {
        return SocialuniChatDOFactory.createGroupChat(userDo.getNickname(), userDo.getUserId(), ChatType.userPersonalGroup, userDo.getAvatar());
    }

    private static SocialuniChatDO createGroupChat(String chatName, Integer userId, String type, String avatar) {
        SocialuniChatDO chatDO = new SocialuniChatDO();

        Integer uid = SocialuniUnionIdFacede.createChatUnionId();
        chatDO.setUnionId(uid);
        chatDO.setChatName(chatName);
        chatDO.setAvatar(avatar);
        chatDO.setContentType(SocialuniContentType.chat);
        chatDO.setType(type);
        chatDO.setOpenType(ChatOpenType.open);
        chatDO.setUserId(userId);
        chatDO = SocialuniRepositoryFacade.save(chatDO);
        return chatDO;
    }
}
