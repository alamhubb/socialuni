package com.socialuni.social.sdk.model.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class SocialuniHomeSwiperModel {
    private Integer id;
    private String status;
    private String name;
    private String skipUrl;
    //备用url，小程序不支持跳转app，可跳转其他页面
    private String standUrl;
    private String standType;
    private Integer devId;
    private String imgUrl;
    private Date createTime;
    private Date updateTime;
    private Integer topLevel;
    private Boolean skip;
    private String skipType;
}
