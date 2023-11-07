package com.socialuni.social.music.sdk.model.RO;

import lombok.Data;

@Data
public class SocialuniMusicInfoRO {
    private Integer id;
    private String musicName;
    private String musicAuthor;
    private String musicImg;
    private Boolean playing;
    private String src;
}
