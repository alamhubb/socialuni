package com.socialuni.social.music.sdk.factory;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicRoomInfoRO;

public class SocialuniMusicRoomPlayerInfoROFactory {
    public static SocialuniMusicRoomInfoRO createSocialuniMusicRoomInfoRO(SocialuniMusicRoomDO socialuniMusicRoomDO, String roleId) {

        SocialuniMusicRoomInfoRO socialuniMusicRoomInfoRO = new SocialuniMusicRoomInfoRO();
        socialuniMusicRoomInfoRO.setMusicUrl(socialuniMusicRoomDO.getMusicUrl());
        socialuniMusicRoomInfoRO.setPlayingTimestamp(socialuniMusicRoomDO.getPlayingTimeStamp());
        socialuniMusicRoomInfoRO.setPlayingTime(socialuniMusicRoomDO.getPlayingTime());
        socialuniMusicRoomInfoRO.setPlaying(socialuniMusicRoomDO.getPlaying());
        socialuniMusicRoomInfoRO.setMusicRoleId(roleId);

        return socialuniMusicRoomInfoRO;
    }
}
