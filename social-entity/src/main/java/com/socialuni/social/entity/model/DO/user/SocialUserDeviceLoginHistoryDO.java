package com.socialuni.social.entity.model.DO.user;

import com.socialuni.social.entity.model.DO.base.CommonBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "s_user_device_login_history")
@Data
@NoArgsConstructor
public class SocialUserDeviceLoginHistoryDO extends CommonBaseDO implements Serializable {
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
