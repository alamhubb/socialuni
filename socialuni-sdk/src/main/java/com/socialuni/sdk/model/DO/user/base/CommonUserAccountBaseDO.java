package com.socialuni.sdk.model.DO.user.base;

import com.socialuni.sdk.model.DO.base.CommonContentBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@MappedSuperclass
@Data
public class CommonUserAccountBaseDO extends CommonContentBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer userId;
    //使用哪个平台注册的
    private String platform;
    private String provider;
    private String appOpenId;
    private String mpOpenId;
    private String h5OpenId;
    private String unionId;
    //对应三方的token
    private String sessionKey;

    private String nickname;
    private String avatar;
    private String gender;
}