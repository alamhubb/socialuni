package com.socialuni.social.music.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

    //可以关联chatGroupId
    Integer roomId;
    // 云端播放器id
    String playerId;
    //是否暂停了
    Boolean pause;

    public SocialuniMusicRoomPlayerDO() {
        this.pause = false;
    }
}