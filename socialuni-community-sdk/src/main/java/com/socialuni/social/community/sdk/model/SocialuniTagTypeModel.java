package com.socialuni.social.community.sdk.model;

import lombok.Data;


import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
public class SocialuniTagTypeModel {

    private Integer id;

    private String name;

    private String status;

    //访问+发帖次数
    private Integer count;
    //本标签共有多少帖子
    private Integer talkCount;

    private Date createTime;

    private Date updateTime;

    private String description;

    //是否显示微信号,处对象，处微友类型显示微信账号
    private Boolean showWxAccount;

    //是否显示在主页，仅显示在话题下，比如炫富话题不适合上热门首页
    private Boolean showInHome;

    private Integer orderLevel;
}
