package com.socialuni.social.music.sdk.factory;

import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicRoomInfoRO;

public class SocialuniMusicRoomPlayerInfoROFactory {
    public static SocialuniMusicRoomInfoRO createSocialuniMusicRoomInfoRO(SocialuniMusicRoomDO socialuniMusicRoomDO) {

        SocialuniMusicRoomInfoRO socialuniMusicRoomInfoRO = new SocialuniMusicRoomInfoRO();
        socialuniMusicRoomInfoRO.setMusicId(socialuniMusicRoomDO.getMusicId());
//        socialuniMusicRoomInfoRO.setMusicTime(socialuniMusicRoomDO.getMusicTime()); 不再记录歌曲信息，只记录播放信息
        socialuniMusicRoomInfoRO.setPlayingTimestamp(socialuniMusicRoomDO.getPlayingTimestamp());
        socialuniMusicRoomInfoRO.setPlayingTime(socialuniMusicRoomDO.getPlayingTime());
        socialuniMusicRoomInfoRO.setPlaying(socialuniMusicRoomDO.getPlaying());
//        socialuniMusicRoomInfoRO.setMusicRoleId(roleId);

        return socialuniMusicRoomInfoRO;
    }
}
