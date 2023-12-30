package com.socialuni.social.sdk.dao.DO.user;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "s_user_device_login_history")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialUserDeviceLoginHistoryDO extends SocialuniBaseDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private Integer userId;
    //登录设备系统
    private String systemInfo;
    //登录渠道，qq，wx，phone
    private String loginProvider;
    //登录平台mp，app
    private String platform;
}
