package com.socialuni.social.music.sdk.logic.manage;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import org.springframework.stereotype.Component;

@Component
public class SocialuniMusicRoomUserManage {

    public SocialuniMusicRoomUserDO getOrCreateMusicRoomUserDO(Integer chatId, Integer mineUserId, String roleId) {
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = SocialuniUserRepositoryFacede.findByUserIdAndCustomFieldAndStatus(mineUserId, "roomId", chatId, SocialuniCommonStatus.enable, SocialuniMusicRoomUserDO.class);

        if (socialuniMusicRoomUserDO == null) {
            //查询用户在房间的权限
            socialuniMusicRoomUserDO = new SocialuniMusicRoomUserDO(mineUserId, chatId, roleId);
            socialuniMusicRoomUserDO = SocialuniUserRepositoryFacede.save(socialuniMusicRoomUserDO);
        }
        return socialuniMusicRoomUserDO;
    }
}
