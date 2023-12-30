package com.socialuni.social.im.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.im.dao.DO.SocialuniUserChatConfigDO;

public class SocialuniUserChatConfigManage {

    public static SocialuniUserChatConfigDO getOrCreateUserChatConfigDO(Integer userId) {
        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserRepositoryFacede.findByUserId(userId, SocialuniUserChatConfigDO.class);

        if (socialuniUserChatConfigDO == null) {
            socialuniUserChatConfigDO = new SocialuniUserChatConfigDO();
            socialuniUserChatConfigDO.setUserId(userId);
            socialuniUserChatConfigDO = SocialuniRepositoryFacade.save(socialuniUserChatConfigDO);
        }
        return socialuniUserChatConfigDO;
    }
}
