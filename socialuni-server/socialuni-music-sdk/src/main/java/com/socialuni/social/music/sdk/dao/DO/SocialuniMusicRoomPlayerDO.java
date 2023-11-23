package com.socialuni.social.music.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name = "s_music_room_player",
        indexes = {
                @Index(columnList = "status")
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"roomId"}),
        }
)
@Data
public class SocialuniMusicRoomPlayerDO extends SocialuniContentBaseDO {
    Integer roomId;

    String musicUrl;
    //默认0
    Date timestamp;
    Integer currentTime;
    //是否暂停了, 默认false
    Boolean playing;

    public SocialuniMusicRoomPlayerDO() {
        this.playing = Boolean.TRUE;
        this.currentTime = 0;
    }
}