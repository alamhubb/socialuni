package com.socialuni.social.music.sdk.model.QO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SocialuniPlayMusicQO {
    @NotBlank
    String musicUrl;
//    @NotNull
//    Integer musicTime;

    // 云端播放器id
    String playerId;
    //当前播放的时间
    //默认 new Date()
    Date playingTimestamp;
    //默认0
    Integer playingTime;
    //是否暂停了, 默认false
    Boolean playing;
}
