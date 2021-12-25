package com.socialuni.social.entity.model.DO.user.base;

import com.socialuni.social.entity.model.DO.CommonContentBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public class SocialUserImgBaseDO extends CommonContentBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer userId;
    private Integer devId;

    @Column(nullable = false, updatable = false)
    private String src;

    @Column(nullable = false, updatable = false)
    private Double aspectRatio;

    @Column(nullable = false)
    private Integer reportNum;

    private String deleteReason;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;

    //是否已认证
    @Column(nullable = false)
    private Boolean isSelfAuth;

    private String content;

    private String violateType;

    private String reportContentType;
}
