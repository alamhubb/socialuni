package com.socialuni.social.tance.sdk.model;

import com.socialuni.social.common.sdk.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DevAccountProviderModler extends SocialuniContentBaseDO implements Serializable {
    private Integer devId;
    private String appId;
    private String appName;

    //目前只支持小程序，未来支持app，记录app
    private String platform;

    private String mpType;

    //内容类型，id，关联商户，关联用户id
    /*@Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String avatarUrl;*/

    //对应三方的统一页面
    /*@Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String threePath;
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String threeTalkPath;*/

    public DevAccountProviderModler(Integer devId, String platform, String mpType) {
        this.devId = devId;
        this.platform = platform;
        this.mpType = mpType;
    }
}
