package com.socialuni.social.sdk.model.model;

import com.socialuni.social.common.model.CommonContentBaseModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class SocialuniHomeSwiperModel extends CommonContentBaseModel {
    private Integer id;
    private String name;
    private String skipUrl;
    //备用url，小程序不支持跳转app，可跳转其他页面
    private String standUrl;
    private String standType;
    private Integer devId;
    private String imgUrl;
    private Integer topLevel;
    private Boolean skip;
    private String skipType;
}
