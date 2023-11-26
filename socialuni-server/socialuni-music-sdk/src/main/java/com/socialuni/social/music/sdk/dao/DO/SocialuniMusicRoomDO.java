package com.socialuni.social.music.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name = "s_music_room",
        indexes = {
                @Index(columnList = "status")
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"roomId"}),
        }
)
@Data
public class SocialuniMusicRoomDO extends SocialuniContentBaseDO {
    Integer roomId;

    String musicUrl;
    //默认0
    Date playingTimeStamp;
    Integer playingTime;
    //是否暂停了, 默认false
    Boolean playing;

    public SocialuniMusicRoomDO() {
        this.playing = Boolean.FALSE;
        this.playingTimeStamp = new Date();
        this.playingTime = 0;
    }
}