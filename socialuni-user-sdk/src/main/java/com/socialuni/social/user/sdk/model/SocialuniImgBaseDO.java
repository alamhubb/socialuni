package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public class SocialuniImgBaseDO extends SocialUnionContentBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private String src;

    @Column(nullable = false, updatable = false)
    private Double aspectRatio;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;
}
