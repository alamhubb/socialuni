package com.socialuni.sdk.model.DO.user.base;

import com.socialuni.sdk.model.DO.base.SocialImgBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public class SocialUserImgBaseDO extends SocialImgBaseDO implements Serializable {
    private Integer devId;

    private String deleteReason;

    //是否已认证
    @Column(nullable = false)
    private Boolean isSelfAuth;

    private String violateType;

    private String reportContentType;
}
