package com.socialuni.social.music.sdk.model.RO;

import lombok.Data;

import java.util.Date;

@Data
public class SocialuniMusicRoomPlayerInfoRO {
    String musicUrl;
    //默认0
    Date playingTimestamp;
    Integer playingTime;
    //是否暂停了, 默认false
    Boolean playing;
}
