package com.socialuni.social.sdk.model.RO.talk;

import com.socialuni.social.common.api.model.SocialuniContentIdRO;
import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * 不需要像帖子一样，每次有回复都刷新，因为不愁看，且你评论后的，有动静你会有通知
 */
@Data
public class SocialuniTalkRO extends SocialuniContentIdRO {
    private SocialuniContentUserRO user;
    private String content;
    private List<SocialuniTalkImgRO> imgs;
    //未来可以修改，但要有记录，修改过就显示已修改，显示修改记录
    private Date updateTime;
    private List<SocialuniCommentRO> comments;

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
    private Boolean hasHugged;
    private Boolean hasFollowed;
    /**
     * talk对应的地理位置
     */
    private SocialTalkDistrictRO district;

    //省
    private String provinceName;
    //市
    private String cityName;
    //区县
    private String districtName;
    //统一标识
    private String adCode;
    private List<String> circles;

    private List<SocialTalkTagRO> tags;

    /**
     * 距离,单位千米
     */
    private Double distance;
    private Integer globalTop;
    private String threeAppName;
    private String threeMpQqAppId;
    private String threeMpWxAppId;
    private String threeAppId;
    private String threeId;
    private String threeTalkPath;
    //是否本开发者自身的内容
    private Boolean threeContent;
    private String visibleGender;
    // 状态
    private String status;
}