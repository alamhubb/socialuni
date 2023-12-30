package com.socialuni.social.common.api.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
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

    public SocialuniImgBaseDo(Integer userId) {
        super(userId);
    }

    public SocialuniImgBaseDo(Integer userId, String contentType, String content) {
        super(userId, contentType, content);
    }

    public SocialuniImgBaseDo(Integer userId, Integer unionId, String contentType, String content) {
        super(userId, unionId, contentType, content);
    }

    public SocialuniImgBaseDo(Integer userId, Integer unionId, String contentType) {
        super(userId, unionId, contentType);
    }
}
