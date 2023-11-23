package com.socialuni.social.music.sdk.factory;

import cn.hutool.core.util.ObjectUtil;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomPlayerDO;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;

import javax.validation.Valid;

public class SocialuniMusicRoomPlayerDOFactory {

    public static SocialuniMusicRoomPlayerDO createSocialuniMusicRoomPlayerDO(@Valid SocialuniPlayMusicQO socialuniPlayMusicQO, SocialuniMusicRoomPlayerDO socialuniMusicRoomPlayerDO) {
        socialuniMusicRoomPlayerDO.setMusicUrl(socialuniPlayMusicQO.getMusicUrl());

        if (ObjectUtil.isNotEmpty(socialuniPlayMusicQO.getTimestamp())) {
            socialuniMusicRoomPlayerDO.setTimestamp(socialuniPlayMusicQO.getTimestamp());
        }
        if (ObjectUtil.isNotEmpty(socialuniPlayMusicQO.getCurrentTime())) {
            socialuniMusicRoomPlayerDO.setCurrentTime(socialuniPlayMusicQO.getCurrentTime());
        }
        if (ObjectUtil.isNotEmpty(socialuniPlayMusicQO.getPause())) {
            socialuniMusicRoomPlayerDO.setPlaying(socialuniPlayMusicQO.getPause());
        }

        socialuniMusicRoomPlayerDO.sequencePlus();

        return socialuniMusicRoomPlayerDO;
    }
}
