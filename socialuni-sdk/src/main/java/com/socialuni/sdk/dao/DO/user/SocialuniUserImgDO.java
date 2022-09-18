package com.socialuni.sdk.dao.DO.user;

import com.socialuni.sdk.dao.DO.base.SocialuniTextCheckDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniImgBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(
        name = "s_user_img",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "userId"),
                @Index(columnList = "updateTime"),
                @Index(columnList = "src"),
                @Index(columnList = "hasPeopleImg"),
                @Index(columnList = "peopleImgIsAdult"),
                @Index(columnList = "hasUnderageContent"),
                @Index(columnList = "hasContactInfo"),
                @Index(columnList = "hasQrCode")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
@Data
public class SocialuniUserImgDO extends SocialuniImgBaseDO implements SocialuniTextCheckDO, Serializable {
    //发表商户
    @Column(nullable = false)
    private Boolean hasPeopleImg;
    @Column(nullable = false)
    private Boolean peopleImgIsAdult;
    //ocr后查看是否包含未成年内容
    @Column(nullable = false)
    private Boolean hasUnderageContent;
    @Column(nullable = false)
    private Boolean hasContactInfo;
    @Column(nullable = false)
    private Boolean hasQrCode;

    //do 只有一个构造
    public SocialuniUserImgDO() {
        this.hasPeopleImg = false;
        this.peopleImgIsAdult = false;
        this.hasUnderageContent = false;
        this.hasContactInfo = false;
        this.hasQrCode = false;
    }
}
