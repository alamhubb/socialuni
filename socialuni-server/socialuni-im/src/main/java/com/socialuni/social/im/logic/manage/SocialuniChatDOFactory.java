package com.socialuni.social.im.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import org.springframework.stereotype.Component;

import java.util.Date;

public class SocialuniChatDOFactory {

    public static Integer getChatIdByCreateSingleChat() {
        SocialuniChatDO chatDO = new SocialuniChatDO();
        chatDO.setType(ChatType.single);
        chatDO = SocialuniRepositoryFacade.save(chatDO);
        return chatDO.getId();
    }
}
