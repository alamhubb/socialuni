package com.socialuni.sdk.dao.DO.talk;

import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class SocialuniImgBaseDO extends SocialUnionContentBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer userId;

    @Column(nullable = false, updatable = false)
    private String src;

    @Column(nullable = false, updatable = false)
    private Double aspectRatio;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;

    //用来存储ocr后的图片内容，用户图片部分也需要相关功能
    @Column(columnDefinition = "text")
    private String content;

    //发表商户
    private Boolean hasPeopleImg;
    private Boolean adultAuth;
    //ocr后查看是否包含未成年内容
    private Boolean hasUnderageContent;

    private Boolean hasQrCode;

    private String deleteReason;

    private String violateType;

    private String contentType;

    @Column(nullable = false)
    private Integer reportNum;

    //do 只有一个构造
    public SocialuniImgBaseDO() {
        this.reportNum = 0;
        this.hasPeopleImg = false;
        this.adultAuth = false;
        this.hasUnderageContent = false;
        this.hasQrCode = false;
    }
}
