package com.socialuni.social.entity.model.DO.user;

import com.socialuni.social.entity.model.DO.CommonBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "s_user_device_login_history")
@Data
@NoArgsConstructor
public class SocialUserDeviceLoginHistoryDO extends CommonBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer userId;
    //登录设备系统
    private String system;
    //登录渠道，qq，wx，phone
    private String loginProvider;
    //登录平台mp，app
    private String platform;
}
