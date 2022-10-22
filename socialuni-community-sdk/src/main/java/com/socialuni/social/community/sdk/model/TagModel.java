package com.socialuni.social.community.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
@Data
@NoArgsConstructor
public class TagModel implements Serializable {

    private Integer id;
    private Integer tagTypeId;

    private String name;

    private String status;
    private String avatar;

    //访问+发帖次数
    private Integer count;
    //本标签共有多少帖子
    private Integer talkCount;

    /**
     * 申请人
     */
    private Integer applyUserId;

    private Date createTime;

    private Date updateTime;

    private String description;

    //是否显示微信号,处对象，处微友类型显示微信账号
    private Boolean showWxAccount;

    //是否显示在主页，仅显示在话题下，比如炫富话题不适合上热门首页
    private Boolean showInHome;

    //仅女生可见，也仅女生可发表
    private String visibleGender;

    //是否在前台展示，app产品，不在前台展示
    private Boolean showFront;
    //devId为唯一是因为根据开发者创建圈子，且不可修改
    private Integer devId;
}
