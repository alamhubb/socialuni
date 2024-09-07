package com.socialuni.social.common.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniImgBaseDo extends SocialuniUnionContentBaseDO {
    @Column(nullable = false, updatable = false)
    private String src;

    @Column(nullable = false, updatable = false)
    private Double aspectRatio;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;

    public SocialuniImgBaseDo() {
    }

    public SocialuniImgBaseDo(Long userId) {
        super(userId);
    }

    public SocialuniImgBaseDo(Long userId, String contentType, String content) {
        super(userId, contentType, content);
    }

    public SocialuniImgBaseDo(Integer devId, Long userId, Long unionId, String contentType, String content) {
        super(devId, userId, unionId, contentType, content);
    }

    public SocialuniImgBaseDo(Integer devId, Long userId, Long unionId, String contentType) {
        super(devId, userId, unionId, contentType);
    }
}
