package com.socialuni.social.music.sdk.model.RO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class SocialuniMusicRoomUserInfoRO {
    String musicRoleId;
    Number musicVolume;
    Boolean soundOff;

    public SocialuniMusicRoomUserInfoRO() {
        this.musicVolume = 0;
        this.soundOff = true;
    }

    public SocialuniMusicRoomUserInfoRO(String musicRoleId) {
        this();
        this.musicRoleId = musicRoleId;
    }
}

