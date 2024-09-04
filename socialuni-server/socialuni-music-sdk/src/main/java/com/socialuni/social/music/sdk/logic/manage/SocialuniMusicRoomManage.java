package com.socialuni.social.music.sdk.logic.manage;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.factory.SocialuniMusicRoomPlayerDOFactory;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class SocialuniMusicRoomManage {
    public SocialuniMusicRoomDO getOrCreateMusicPlayerDO(Long chatId) {
        SocialuniMusicRoomDO socialuniMusicRoomPlayerDO = SocialuniRepositoryFacade.findByCustomField("roomId", chatId, SocialuniMusicRoomDO.class);

        if (socialuniMusicRoomPlayerDO == null) {
            socialuniMusicRoomPlayerDO = SocialuniMusicRoomPlayerDOFactory.createSocialuniMusicRoomPlayerDO(chatId);
        }

        socialuniMusicRoomPlayerDO = SocialuniRepositoryFacade.save(socialuniMusicRoomPlayerDO);

        return socialuniMusicRoomPlayerDO;
    }


    public SocialuniMusicRoomDO updateMusicPlayerDO(Long chatId, SocialuniPlayMusicQO playMusicQO) {
        SocialuniMusicRoomDO socialuniMusicRoomDO = this.getOrCreateMusicPlayerDO(chatId);

        //获取用户在房间的权限
        //判断用户是否有播放权限
//        SocialuniMusicRoomPlayerDOFactory.createSocialuniMusicRoomPlayerDO(playMusicQO, socialuniMusicRoomDO);


        socialuniMusicRoomDO = this.updateMusicPlayerDO(socialuniMusicRoomDO);

        return socialuniMusicRoomDO;
    }

    public SocialuniMusicRoomDO updateMusicPlayerDO(SocialuniMusicRoomDO socialuniMusicRoomDO) {
        Integer newSequence = socialuniMusicRoomDO.getSequenceNum() + 1;

        Long roomId = socialuniMusicRoomDO.getRoomId();

        SocialuniMusicRoomDO socialuniMusicRoomPlayerDO = SocialuniRepositoryFacade.findByCustomField("roomId", roomId, SocialuniMusicRoomDO.class);

        if (newSequence > socialuniMusicRoomPlayerDO.getSequenceNum()) {

            socialuniMusicRoomPlayerDO.setSequenceNum(newSequence);
            socialuniMusicRoomDO = SocialuniRepositoryFacade.save(socialuniMusicRoomDO);
            return socialuniMusicRoomDO;
        }
        throw new SocialParamsException("播放信息变化，更新失败，请重新尝试");
    }


    public SocialuniMusicRoomDO playMusic(Integer musicId, SocialuniPlayMusicQO playMusicQO, SocialuniMusicRoomDO socialuniMusicRoomDO) {

        SocialuniMusicRoomPlayerDOFactory.createSocialuniMusicRoomPlayerDO(musicId, playMusicQO, socialuniMusicRoomDO);

        this.updateMusicPlayerDO(socialuniMusicRoomDO);

        return socialuniMusicRoomDO;
    }
}
