package com.socialuni.sdk.dao.DO.base;

import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class SocialImgBaseDO extends SocialUnionContentBaseDO implements Serializable {
    private Integer contentId;

    @Column(nullable = false, updatable = false)
    private Integer userId;

    @Column(nullable = false, updatable = false)
    private String src;

    @Column(nullable = false, updatable = false)
    private Double aspectRatio;

    @Column(nullable = false)
    private Integer reportNum;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;

    private String content;

    public SocialImgBaseDO() {
        this.reportNum = 0;
    }
}