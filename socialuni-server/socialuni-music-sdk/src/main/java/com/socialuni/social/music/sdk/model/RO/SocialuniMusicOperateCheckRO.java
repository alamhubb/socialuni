package com.socialuni.social.music.sdk.model.RO;

import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import lombok.Data;

@Data
public class SocialuniMusicOperateCheckRO {
    Integer chatId;
    Integer mineUserId;
    String roleId;
    SocialuniMusicRoomDO socialuniMusicRoomDO;
    SocialuniMusicRoomUserDO socialuniMusicRoomUserDO;

    public SocialuniMusicOperateCheckRO(Integer chatId, Integer mineUserId, String roleId) {
        this.chatId = chatId;
        this.mineUserId = mineUserId;
        this.roleId = roleId;
    }

    public SocialuniMusicOperateCheckRO(Integer chatId, Integer mineUserId, SocialuniMusicRoomDO socialuniMusicRoomDO, SocialuniMusicRoomUserDO socialuniMusicRoomUserDO) {
        this.chatId = chatId;
        this.mineUserId = mineUserId;
        if (socialuniMusicRoomUserDO != null) {
            this.roleId = socialuniMusicRoomUserDO.getRoomRoleId();
        }
        this.socialuniMusicRoomDO = socialuniMusicRoomDO;
        this.socialuniMusicRoomUserDO = socialuniMusicRoomUserDO;
    }
}
