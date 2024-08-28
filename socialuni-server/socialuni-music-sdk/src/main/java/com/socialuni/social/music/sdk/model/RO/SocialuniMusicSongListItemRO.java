package com.socialuni.social.music.sdk.model.RO;

import lombok.Data;

@Data
public class SocialuniMusicSongListItemRO {
    //内部音乐Id
    private Integer musicId;
    //外部音乐Id
    private String songId;
    private Integer no;
    private String musicName;
    private String author;
    private Integer musicTime;
    private String musicUrl;

    //专辑和专辑图片，前端展示需要
    private String album;
    private String albumImg;

//    private String musicImg;
//    private Boolean playing;
//    private String musicSrc;
}
