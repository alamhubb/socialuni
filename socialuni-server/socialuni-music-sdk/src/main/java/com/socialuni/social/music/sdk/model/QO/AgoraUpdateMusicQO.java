package com.socialuni.social.music.sdk.model.QO;

import lombok.Data;

@Data
public class AgoraUpdateMusicQO {
    Boolean isPause;
    String streamUrl;
    Long seekPosition;
}
