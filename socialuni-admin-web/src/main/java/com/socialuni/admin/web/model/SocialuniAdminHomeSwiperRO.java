package com.socialuni.admin.web.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class SocialuniAdminHomeSwiperRO {
    private Integer id;
    private String status;
    //跳转到的页面的title
    private String name;
    //跳转的页面
    private Boolean skip;
    private String skipType;
    private String skipUrl;
    //备用url，小程序不支持跳转app，可跳转其他页面
    private String standUrl;
    private String standType;
    private String imgUrl;
    private Integer topLevel;
}
