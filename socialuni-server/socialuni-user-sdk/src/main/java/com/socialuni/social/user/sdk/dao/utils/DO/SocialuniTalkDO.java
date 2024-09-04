package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.content.dao.DO.SocialuniTextCheckDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_community_talk",
        indexes = {
                @Index(columnList = "devId"),
                @Index(columnList = "status"),
                @Index(columnList = "userId"),
                @Index(columnList = "updateTime"),
                @Index(columnList = "visibleGender"),
                @Index(columnList = "visibleType"),
                @Index(columnList = "adCode"),
                @Index(columnList = "createTime"), @Index(columnList = "contentType"),
                @Index(columnList = "globalTop"), @Index(columnList = "hasPeopleImg"),
                @Index(columnList = "peopleImgIsAdult"), @Index(columnList = "hasUnderageContent"),
                @Index(columnList = "hasContactInfo"), @Index(columnList = "hasQrCode")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniTalkDO extends SocialuniUnionContentBaseDO implements SocialuniTextCheckDO, Serializable {
    @Column(nullable = false)
    Integer devId;
    //为什么不存thirdUserId，因为根据userId+devId可以确认thirdUserId
    /**
     * 评论数量
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer commentNum;
    /**
     * 抱抱次数
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer hugNum;

    /**
     * 全局置顶标识，默认0，数越大级别越高
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer globalTop;


    @Column(nullable = false, columnDefinition = "varchar(255) default 'fullNetwork'")
    private String visibleType;
    //可见性别
    @Column(nullable = false, columnDefinition = "varchar(255) default 'all'")
    private String visibleGender;
    private String adCode;
    private String adName;
    private String cityName;
    //省
    private String provinceName;
    //区县
    private String districtName;

    /*
     *  经度 Longitude 简写Lng
     * 纬度范围-90~90，经度范围-180~180
     */
    private Double lon;
    /*
     * 纬度 Latitude 简写Lat
     */
    private Double lat;
    //最大最小可见年龄
    private Integer minAge;
    private Integer maxAge;

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
    //因为talk有talkImgs所以有这个属性
    @Column(nullable = false)
    private Boolean hasQrCode;

    //do 只有一个构造
    public SocialuniTalkDO(Long userId, String content) {
        super(userId, SocialuniContentType.talk, content);
        this.commentNum = 0;
        this.hugNum = 0;
        this.globalTop = 0;

        this.peopleImgIsAdult = false;
        this.hasUnderageContent = false;
        this.hasContactInfo = false;

        //云api相关
        this.hasPeopleImg = false;
        this.hasQrCode = false;
    }
}
