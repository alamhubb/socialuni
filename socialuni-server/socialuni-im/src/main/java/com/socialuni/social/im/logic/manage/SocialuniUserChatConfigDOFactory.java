package com.socialuni.social.im.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.SocialuniUserChatConfigDO;
import com.socialuni.social.im.enumeration.ChatType;

public class SocialuniUserChatConfigDOFactory {

    public static SocialuniUserChatConfigDO getOrCreateUserChatConfigDO(Integer userId) {
        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniRepositoryFacade.findById(userId, SocialuniUserChatConfigDO.class);

        if (socialuniUserChatConfigDO == null) {
            socialuniUserChatConfigDO = new SocialuniUserChatConfigDO();
            socialuniUserChatConfigDO.setUserId(userId);
            socialuniUserChatConfigDO = SocialuniRepositoryFacade.save(socialuniUserChatConfigDO);
        }
        return socialuniUserChatConfigDO;
    }
}
