package com.socialuni.social.music.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "s_music_room_player",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "status")
        }
)
@Data
@NoArgsConstructor
public class SocialuniMusicRoomPlayerDO extends SocialuniContentBaseDO {

    //可以关联chatGroupId
    Integer roomId;
    // 云端播放器id
    String playerId;

}