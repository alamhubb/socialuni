package com.socialuni.sdk.dao.DO.talk;

import com.socialuni.sdk.dao.DO.SocialCommonBaseDO;
import com.socialuni.sdk.dao.DO.SocialContentBaseDO;
import com.socialuni.sdk.dao.DO.base.SocialImgBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_talk_img",
        indexes = {
                @Index(columnList = "contentId")
        }
)
@Data
public class SocialTalkImgDO extends SocialContentBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer contentId;

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
    private String content;

    //发表商户
    private Boolean hasPeopleImg;
    private Boolean adultAuth;

    //do 只有一个构造
    public SocialTalkImgDO() {
        this.hasPeopleImg = false;
        this.adultAuth = false;
    }
}
