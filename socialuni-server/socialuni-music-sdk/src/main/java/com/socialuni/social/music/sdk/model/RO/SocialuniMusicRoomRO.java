package com.socialuni.social.music.sdk.model.RO;

import lombok.Data;

@Data
public class SocialuniMusicRoomRO {
    String musicUrl;
    //是否处于暂停状态
    String pause;
    //正在播放的时间
    Integer currentTime;
}
