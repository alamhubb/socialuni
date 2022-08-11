package com.socialuni.sdk.model.DO;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2020-05-23 17:21
 */
@Data
@Entity
/**
 * 全局配置表
 */
@Table(name = "home_swiper")
public class HomeSwiperDO  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(columnDefinition = "bit default true")
    private Boolean skip;
    @Column(columnDefinition = "varchar(255) default 'web'")
    private String skipType;
}
