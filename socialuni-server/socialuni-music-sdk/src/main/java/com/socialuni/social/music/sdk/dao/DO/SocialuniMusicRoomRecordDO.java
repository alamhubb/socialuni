package com.socialuni.social.music.sdk.dao.DO;

import com.qingchi.qing.jpa.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "s_music_room_record",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "status")
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniMusicRoomRecordDO extends SocialuniContentBaseDO {

    //可以关联chatGroupId
    Long roomId;

    //操作类型
    String operateType;


    // 云端播放器id
//    String playerId;

}