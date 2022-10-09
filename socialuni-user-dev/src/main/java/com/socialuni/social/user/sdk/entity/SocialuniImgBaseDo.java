package com.socialuni.social.user.sdk.entity;

import com.socialuni.social.user.sdk.model.SocialuniImgBaseModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class SocialuniImgBaseDo extends SocialuniImgBaseModel {
    @Column(nullable = false, updatable = false)
    private String src;

    @Column(nullable = false, updatable = false)
    private Double aspectRatio;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;
}
