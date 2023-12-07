package com.socialuni.social.music.sdk.model.RO;

import lombok.Data;

import java.util.Date;

@Data
public class SocialuniMusicRoomInfoRO {
//    String musicRoleId;
    String musicUrl;
    //默认0
    Date playingTimestamp;
    Integer playingTime;
    Integer musicTime;
    //是否暂停了, 默认false
    Boolean playing;
}
