package com.socialuni.social.music.sdk.model.QO;

import lombok.Data;

@Data
public class AgoraPlayMusicPlayerQO {
    String streamUrl;
    String channelName;
    String token;
    Integer uid;
    Integer idleTimeout;
}
