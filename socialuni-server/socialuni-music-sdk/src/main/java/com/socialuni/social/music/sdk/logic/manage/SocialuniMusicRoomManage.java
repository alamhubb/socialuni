package com.socialuni.social.music.sdk.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.factory.SocialuniMusicRoomPlayerDOFactory;
import org.springframework.stereotype.Component;


@Component
public class SocialuniMusicRoomManage {
    public SocialuniMusicRoomDO getOrCreateMusicPlayerDO(Integer chatId) {
        SocialuniMusicRoomDO socialuniMusicRoomPlayerDO = SocialuniRepositoryFacade.findByCustomField("roomId", chatId, SocialuniMusicRoomDO.class);

        if (socialuniMusicRoomPlayerDO == null) {
            socialuniMusicRoomPlayerDO = SocialuniMusicRoomPlayerDOFactory.createSocialuniMusicRoomPlayerDO(chatId);
        }

        socialuniMusicRoomPlayerDO = SocialuniRepositoryFacade.save(socialuniMusicRoomPlayerDO);

        return socialuniMusicRoomPlayerDO;
    }
}
