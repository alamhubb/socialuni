package com.socialuni.social.music.sdk.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.factory.SocialuniMusicRoomPlayerDOFactory;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;
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


    public SocialuniMusicRoomDO updateMusicPlayerDO(Integer chatId, SocialuniPlayMusicQO playMusicQO) {
        SocialuniMusicRoomDO socialuniMusicRoomDO = this.getOrCreateMusicPlayerDO(chatId);

        //获取用户在房间的权限
        //判断用户是否有播放权限
        SocialuniMusicRoomPlayerDOFactory.createSocialuniMusicRoomPlayerDO(playMusicQO, socialuniMusicRoomDO);


        socialuniMusicRoomDO = SocialuniRepositoryFacade.save(socialuniMusicRoomDO);

        return socialuniMusicRoomDO;
    }
}
