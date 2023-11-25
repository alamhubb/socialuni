package com.socialuni.social.music.sdk.factory;

import cn.hutool.core.util.ObjectUtil;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomPlayerDO;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;

import javax.validation.Valid;

public class SocialuniMusicRoomPlayerDOFactory {
    public static SocialuniMusicRoomPlayerDO createSocialuniMusicRoomPlayerDO(Integer roomId) {
        //播放，是否存在
        SocialuniMusicRoomPlayerDO socialuniMusicRoomPlayerDO = new SocialuniMusicRoomPlayerDO();
        socialuniMusicRoomPlayerDO.setRoomId(roomId);

        return socialuniMusicRoomPlayerDO;
    }

    public static SocialuniMusicRoomPlayerDO createSocialuniMusicRoomPlayerDO(@Valid SocialuniPlayMusicQO socialuniPlayMusicQO, SocialuniMusicRoomPlayerDO socialuniMusicRoomPlayerDO) {
        socialuniMusicRoomPlayerDO.setMusicUrl(socialuniPlayMusicQO.getMusicUrl());

        if (ObjectUtil.isNotEmpty(socialuniPlayMusicQO.getPlayingTimeStamp())) {
            socialuniMusicRoomPlayerDO.setPlayingTimeStamp(socialuniPlayMusicQO.getPlayingTimeStamp());
        }
        if (ObjectUtil.isNotEmpty(socialuniPlayMusicQO.getPlayingTime())) {
            socialuniMusicRoomPlayerDO.setPlayingTime(socialuniPlayMusicQO.getPlayingTime());
        }
        if (ObjectUtil.isNotEmpty(socialuniPlayMusicQO.getPause())) {
            socialuniMusicRoomPlayerDO.setPlaying(socialuniPlayMusicQO.getPause());
        }

        socialuniMusicRoomPlayerDO.sequencePlus();

        return socialuniMusicRoomPlayerDO;
    }
}
