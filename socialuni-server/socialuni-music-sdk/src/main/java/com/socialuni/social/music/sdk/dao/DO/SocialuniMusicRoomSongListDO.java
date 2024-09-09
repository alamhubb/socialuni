package com.socialuni.social.music.sdk.dao.DO;

import com.qingchi.qing.jpa.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "s_music_room_song_list",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "roomId")
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniMusicRoomSongListDO extends SocialuniContentBaseDO {

    //序号
    Integer orderNo;

    Long roomId;

    String songId;

    String musicName;
    String author;
    //单位，毫秒
    Integer musicTime;

    String album;
    String albumImg;
    String musicUrl;
}