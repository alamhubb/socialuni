package com.socialuni.social.community.sdk.model;

import lombok.Data;

import java.util.Date;

@Data
public class TalkEO {
    private Integer id;

    private Date updateTime;
    private Integer userId;

    private String content;


    private Integer talkId;
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

//    private String visibleType;
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
    //发表商户
    private Integer devId;
    //三方id，只有为三方自己查询的时候才显示
    private String threeId;
}
