package com.socialuni.social.im.logic.foctory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class SocialuniChatDOFactory {

    public static SocialuniChatDO getChatIdByCreateSingleChat() {
        SocialuniChatDO chatDO = new SocialuniChatDO();

        Integer uid = SocialuniUnionIdFacede.createChatUnionId();
        chatDO.setUnionId(uid);
        chatDO.setContentType(SocialuniContentType.chat);
        chatDO.setType(ChatType.single);
        chatDO = SocialuniRepositoryFacade.save(chatDO);
        return chatDO;
    }

    public static SocialuniChatDO createSystemGroupChat(String chatName) {
        return SocialuniChatDOFactory.createGroupChat(chatName, null, ChatType.system_group, SocialuniSystemConst.getTagDefaultAvatar());
    }

    public static SocialuniChatDO createUserRoomGroupChat(SocialuniUserDo userDo) {
        return SocialuniChatDOFactory.createGroupChat(userDo.getNickname(), userDo.getUserId(), ChatType.user_room_group, userDo.getAvatar());
    }

    private static SocialuniChatDO createGroupChat(String chatName, Integer userId, String type, String avatar) {
        SocialuniChatDO chatDO = new SocialuniChatDO();

        Integer uid = SocialuniUnionIdFacede.createChatUnionId();
        chatDO.setUnionId(uid);
        chatDO.setChatName(chatName);
        chatDO.setAvatar(avatar);
        chatDO.setContentType(SocialuniContentType.chat);
        chatDO.setType(type);
        chatDO.setUserId(userId);
        chatDO = SocialuniRepositoryFacade.save(chatDO);
        return chatDO;
    }
}
