package com.socialuni.social.user.sdk.dao.DO;

import com.qingchi.qing.jpa.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "s_user_login_history")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialUserDeviceLoginHistoryDO extends SocialuniUserInfoBaseDO implements Serializable {
    //渠道登录，手机登录，设备登录
    private String loginType;

    //登录设备系统,systemType
    private String systemInfo;

    //登录渠道，qq，wx
    private String loginProvider;

    //登录平台mp，app,h5
    private String platform;

    private String deviceUid;
}
