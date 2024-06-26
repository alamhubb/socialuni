package com.socialuni.social.userImg.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniImgBaseDo;
import com.socialuni.social.content.dao.DO.SocialuniTextCheckDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Entity
@Table(
        name = "s_user_img",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "userId"),
                @Index(columnList = "updateTime"),
                @Index(columnList = "createTime"),
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
@EqualsAndHashCode(callSuper = true)
public class SocialuniUserImgDo extends SocialuniImgBaseDo implements SocialuniTextCheckDO {
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
    public SocialuniUserImgDo() {
        this.hasPeopleImg = false;
        this.peopleImgIsAdult = false;
        this.hasUnderageContent = false;
        this.hasContactInfo = false;
        this.hasQrCode = false;
    }
}
