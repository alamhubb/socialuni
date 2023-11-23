package com.socialuni.social.music.sdk.factory;

import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomPlayerDO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicRoomPlayerInfoRO;

public class SocialuniMusicRoomPlayerInfoROFactory {
    public static SocialuniMusicRoomPlayerInfoRO createSocialuniMusicRoomPlayerInfoRO(SocialuniMusicRoomPlayerDO socialuniMusicRoomPlayerDO) {
        SocialuniMusicRoomPlayerInfoRO socialuniMusicRoomPlayerInfoRO = new SocialuniMusicRoomPlayerInfoRO();
        socialuniMusicRoomPlayerInfoRO.setMusicUrl(socialuniMusicRoomPlayerDO.getMusicUrl());
        socialuniMusicRoomPlayerInfoRO.setTimestamp(socialuniMusicRoomPlayerDO.getTimestamp());
        socialuniMusicRoomPlayerInfoRO.setCurrentTime(socialuniMusicRoomPlayerDO.getCurrentTime());
        socialuniMusicRoomPlayerInfoRO.setPlaying(socialuniMusicRoomPlayerDO.getPlaying());
        return socialuniMusicRoomPlayerInfoRO;
    }
}
