package com.socialuni.social.im.logic.foctory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;

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

    public static SocialuniChatDO createGroupChat(String chatName) {
        SocialuniChatDO chatDO = new SocialuniChatDO();

        Integer uid = SocialuniUnionIdFacede.createChatUnionId();
        chatDO.setUnionId(uid);
        chatDO.setChatName(chatName);
        chatDO.setAvatar(SocialuniSystemConst.getTagDefaultAvatar());
        chatDO.setContentType(SocialuniContentType.chat);
        chatDO.setType(ChatType.system_group);
        chatDO = SocialuniRepositoryFacade.save(chatDO);
        return chatDO;
    }
}
