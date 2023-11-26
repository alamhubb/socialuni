package com.socialuni.social.music.sdk.factory;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomPlayerDO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicRoomPlayerInfoRO;

public class SocialuniMusicRoomPlayerInfoROFactory {
    public static SocialuniMusicRoomPlayerInfoRO createSocialuniMusicRoomPlayerInfoRO(SocialuniMusicRoomPlayerDO socialuniMusicRoomPlayerDO,Integer userId) {

        SocialuniMusicRoomPlayerInfoRO socialuniMusicRoomPlayerInfoRO = new SocialuniMusicRoomPlayerInfoRO();
        socialuniMusicRoomPlayerInfoRO.setMusicUrl(socialuniMusicRoomPlayerDO.getMusicUrl());
        socialuniMusicRoomPlayerInfoRO.setPlayingTimestamp(socialuniMusicRoomPlayerDO.getPlayingTimeStamp());
        socialuniMusicRoomPlayerInfoRO.setPlayingTime(socialuniMusicRoomPlayerDO.getPlayingTime());
        socialuniMusicRoomPlayerInfoRO.setPlaying(socialuniMusicRoomPlayerDO.getPlaying());


        Integer roomId = socialuniMusicRoomPlayerDO.getRoomId();

        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = SocialuniUserRepositoryFacede.findByUserIdAndCustomFieldAndStatus(userId, "roomId", roomId, SocialuniCommonStatus.enable, SocialuniMusicRoomUserDO.class);

        socialuniMusicRoomPlayerInfoRO.setMusicRoleId(socialuniMusicRoomUserDO.getRoomRoleId());
        return socialuniMusicRoomPlayerInfoRO;
    }
}
