package com.socialuni.social.user.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户扩列好友表
@Entity
@Table(name = "s_user_extend_friend_log",
        indexes = {
                @Index(columnList = "adCode"),
                @Index(columnList = "updateTime"),
        })
@Data
@NoArgsConstructor
public class SocialuniUserExtendFriendLogDo extends SocialuniContentBaseDO {
    @Column(nullable = false)
    private String lon;
    /*
     * 纬度 Latitude 简写Lat
     */
    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String adCode;
}