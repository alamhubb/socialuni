package com.socialuni.social.community.sdk.model;

import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.user.sdk.api.SocialuniTextCheckDO;
import com.socialuni.social.user.sdk.model.SocialuniUnionContentBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniTalkModel extends SocialuniUnionContentBaseModel implements SocialuniTextCheckDO, Serializable {
    Integer devId;
    //为什么不存thirdUserId，因为根据userId+devId可以确认thirdUserId
    /**
     * 评论数量
     */
    private Integer commentNum;
    /**
     * 抱抱次数
     */
    private Integer hugNum;

    /**
     * 全局置顶标识，默认0，数越大级别越高
     */
    private Integer globalTop;


    private String visibleType;
    //可见性别
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
    private Boolean hasPeopleImg;
    private Boolean peopleImgIsAdult;
    //ocr后查看是否包含未成年内容
    private Boolean hasUnderageContent;
    private Boolean hasContactInfo;
    //因为talk有talkImgs所以有这个属性
    private Boolean hasQrCode;

    //do 只有一个构造
    public SocialuniTalkModel(Integer userId, String content) {
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
