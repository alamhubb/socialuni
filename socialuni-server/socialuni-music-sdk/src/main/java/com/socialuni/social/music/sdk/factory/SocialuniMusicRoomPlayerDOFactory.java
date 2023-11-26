package com.socialuni.social.music.sdk.factory;

import cn.hutool.core.util.ObjectUtil;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;

import javax.validation.Valid;
import java.util.Date;

public class SocialuniMusicRoomPlayerDOFactory {
    public static SocialuniMusicRoomDO createSocialuniMusicRoomPlayerDO(Integer roomId) {
        //播放，是否存在
        SocialuniMusicRoomDO socialuniMusicRoomPlayerDO = new SocialuniMusicRoomDO();
        socialuniMusicRoomPlayerDO.setRoomId(roomId);

        return socialuniMusicRoomPlayerDO;
    }

    public static SocialuniMusicRoomDO createSocialuniMusicRoomPlayerDO(@Valid SocialuniPlayMusicQO socialuniPlayMusicQO, SocialuniMusicRoomDO socialuniMusicRoomPlayerDO) {
        socialuniMusicRoomPlayerDO.setMusicUrl(socialuniPlayMusicQO.getMusicUrl());

        if (ObjectUtil.isNotEmpty(socialuniPlayMusicQO.getPlayingTimeStamp())) {
            socialuniMusicRoomPlayerDO.setPlayingTimeStamp(socialuniPlayMusicQO.getPlayingTimeStamp());
        } else {
            socialuniMusicRoomPlayerDO.setPlayingTimeStamp(new Date());
        }

        if (ObjectUtil.isNotEmpty(socialuniPlayMusicQO.getPlayingTime())) {
            socialuniMusicRoomPlayerDO.setPlayingTime(socialuniPlayMusicQO.getPlayingTime());
        } else {
            socialuniMusicRoomPlayerDO.setPlayingTime(0);
        }
        //如果为空默认为播放
        if (ObjectUtil.isEmpty(socialuniPlayMusicQO.getPause())) {
            socialuniMusicRoomPlayerDO.setPlaying(true);
        } else {
            socialuniMusicRoomPlayerDO.setPlaying(socialuniPlayMusicQO.getPause());
        }

        socialuniMusicRoomPlayerDO.sequencePlus();

        return socialuniMusicRoomPlayerDO;
    }
}
