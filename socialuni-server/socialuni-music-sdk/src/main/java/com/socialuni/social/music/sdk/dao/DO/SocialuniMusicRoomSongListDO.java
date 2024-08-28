package com.socialuni.social.music.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

    Integer roomId;

    String songId;

    String musicName;
    String author;
    Integer musicTime;

    String album;
    String albumImg;
    String musicUrl;
}