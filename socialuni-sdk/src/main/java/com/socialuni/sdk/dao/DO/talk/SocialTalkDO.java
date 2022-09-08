package com.socialuni.sdk.dao.DO.talk;

import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.sdk.constant.socialuni.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_talk", indexes = {
        @Index(columnList = "visibleGender"),
        @Index(columnList = "visibleType"),
        @Index(columnList = "adCode"),
        @Index(columnList = "status"),
        @Index(columnList = "createTime"),
        @Index(columnList = "updateTime"),
        @Index(columnList = "userId"),
        @Index(columnList = "socialuniUid"),
        @Index(columnList = "content"),
        @Index(columnList = "reportContentType"),
        @Index(columnList = "unionId"),
        @Index(columnList = "globalTop")

})
@Data
@NoArgsConstructor
public class SocialTalkDO extends SocialUnionContentBaseDO implements BaseModelDO, Serializable {
    //为什么不存thirdUserId，因为根据userId+devId可以确认thirdUserId
    private String socialuniUid;
    //内容主体
    private String content;

    /**
     * 评论数量
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer commentNum;
    private Integer devId;
    /**
     * 抱抱次数
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer hugNum;
    /**
     * 举报次数
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer reportNum;
    @Column(nullable = false)
    private Integer userId;

    /**
     * 全局置顶标识，默认0，数越大级别越高
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer globalTop;

    /**
     * 删除原因
     */
    private String deleteReason;
    private String reportContentType;
    private String violateType;
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
    private Boolean hasPeopleImg;
    private Boolean identityAuth;

    //do 只有一个构造
    public SocialTalkDO(Integer userId, String content) {
        this.userId = userId;
        this.content = content;
        this.commentNum = 0;
        this.hugNum = 0;
        this.reportNum = 0;
        this.globalTop = 0;
        this.reportContentType = ContentType.talk;
        this.hasPeopleImg = false;
        this.identityAuth = false;
    }
}
