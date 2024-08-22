package com.socialuni.social.music.sdk.model.QO;

import lombok.Data;

@Data
public class SocialuniMusicRoomPlaySongQO {

    Integer songId;

    Integer roomId;

    String musicName;
    String musicSinger;
    Integer musicTime;

}
