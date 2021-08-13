package com.socialuni.social.entity.model.DO.talk;

import com.socialuni.constant.ContentType;
import com.socialuni.social.entity.model.DO.CommonContentBaseDO;
import com.socialuni.social.entity.model.DO.base.BaseModelDO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "talk", indexes = {
        @Index(columnList = "visibleGender"),
        @Index(columnList = "visibleType"),
        @Index(columnList = "adCode"),
        @Index(columnList = "status"),
        @Index(columnList = "devId"),
        @Index(columnList = "createTime"),
        @Index(columnList = "updateTime"),
        @Index(columnList = "userId"),
        @Index(columnList = "socialuniUid"),
        @Index(columnList = "content"),
        @Index(columnList = "reportContentType"),
        @Index(columnList = "globalTop"),
})
@Data
public class TalkDO extends CommonContentBaseDO implements BaseModelDO {
    //为什么不存thirdUserId，因为根据userId+devId可以确认thirdUserId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String socialuniUid;
    //内容主体
    private String content;

    /**
     * 评论数量
     */
    private Integer commentNum;
    /**
     * 抱抱次数
     */
    private Integer hugNum;
    /**
     * 举报次数
     */
    private Integer reportNum;

    private Integer userId;

    /**
     * 全局置顶标识，默认0，数越大级别越高
     */
    private Integer globalTop;

    /**
     * 删除原因
     */
    private String deleteReason;
    private String reportContentType;
    private String violateType;
    @Column(columnDefinition = "varchar(255) default 'fullNetwork'")
    private String visibleType;
    //可见性别
    @Column(columnDefinition = "varchar(255) default 'all'")
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
    @Column(columnDefinition = "int default 1")
    private Integer devId;

    public TalkDO() {
        this.commentNum = 0;
        this.hugNum = 0;
        this.reportNum = 0;
        this.globalTop = 0;
        this.reportContentType = ContentType.talk;
    }

    //do 只有一个构造
    public TalkDO(Integer userId, String content) {
        this();
        this.userId = userId;
        this.content = content;
    }
}
