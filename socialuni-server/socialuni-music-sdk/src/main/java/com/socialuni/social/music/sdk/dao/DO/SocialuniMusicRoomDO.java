package com.socialuni.social.music.sdk.dao.DO;

import com.qingchi.qing.jpa.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
public class SocialuniMusicRoomDO extends SocialuniContentBaseDO {
    Long roomId;

    //songList表的id
    Integer musicId;
//    Integer musicTime;
    //默认0
    Date playingTimestamp;
    Integer playingTime;
    //是否暂停了, 默认false
    Boolean playing;

    public SocialuniMusicRoomDO() {
        this.playing = Boolean.FALSE;
        this.playingTimestamp = new Date();
        this.playingTime = 0;
    }
}