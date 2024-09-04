package com.socialuni.social.music.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "s_music_room_user",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "roomId"),
                @Index(columnList = "status")
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniMusicRoomUserDO extends SocialuniContentBaseDO {

    //可以关联chatGroupId
    // 云端播放器id
    Long roomId;
    String roomRoleId;

    public SocialuniMusicRoomUserDO(Long userId,  Long roomId, String roomRoleId) {
        super(userId);
        this.roomId = roomId;
        this.roomRoleId = roomRoleId;
    }
}