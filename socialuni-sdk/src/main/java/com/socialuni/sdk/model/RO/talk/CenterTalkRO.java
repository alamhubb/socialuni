package com.socialuni.sdk.model.RO.talk;

import com.socialuni.sdk.factory.RO.talk.CenterCommentROFactory;
import com.socialuni.sdk.factory.RO.talk.CenterTalkImgROFactory;
import com.socialuni.sdk.model.RO.community.UniContentIdRO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkDistrictRO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkTagRO;
import com.socialuni.sdk.model.RO.user.CenterContentUserRO;
import com.socialuni.sdk.constant.socialuni.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 不需要像帖子一样，每次有回复都刷新，因为不愁看，且你评论后的，有动静你会有通知
 */
@Data
@NoArgsConstructor
public class CenterTalkRO extends UniContentIdRO {
    private CenterContentUserRO user;
    private List<CenterTalkImgRO> imgs;
    private List<CenterCommentRO> comments;
    /**
     * talk对应的地理位置
     */
    private SocialTalkDistrictRO district;
    private List<SocialTalkTagRO> tags;
    private List<String> circles;

    private String content;
    //未来可以修改，但要有记录，修改过就显示已修改，显示修改记录
    private Date updateTime;
    /**
     * 评论数量
     */
    private Integer commentNum;
    private Integer reportNum;
    /**
     * 抱抱次数
     */
    private Integer hugNum;
    /**
     * 举报次数
     */
    private Boolean hasHugged;
    private Boolean hasFollowed;


    //省
    private String provinceName;
    //市
    private String cityName;
    //区县
    private String districtName;
    //统一标识
    private String adCode;

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

    public CenterTalkRO(CenterTalkRO centerTalkRO) {
        super(ContentType.talk, centerTalkRO);
        this.user = new CenterContentUserRO(centerTalkRO.getUser());
        this.imgs = CenterTalkImgROFactory.getCenterTalkImgROS(centerTalkRO.getImgs());
        this.comments = CenterCommentROFactory.getCommentROs(centerTalkRO.getComments());

        this.tags = centerTalkRO.getTags();
        this.circles = centerTalkRO.getCircles();
        this.district = centerTalkRO.getDistrict();

        this.content = centerTalkRO.getContent();
        this.updateTime = centerTalkRO.getUpdateTime();
        this.commentNum = centerTalkRO.getCommentNum();
        this.reportNum = centerTalkRO.getReportNum();
        this.hugNum = centerTalkRO.getHugNum();
        this.hasHugged = centerTalkRO.getHasHugged();
        this.hasFollowed = centerTalkRO.getHasFollowed();
        this.provinceName = centerTalkRO.getProvinceName();
        this.cityName = centerTalkRO.getCityName();
        this.districtName = centerTalkRO.getDistrictName();
        this.adCode = centerTalkRO.getAdCode();
        this.distance = centerTalkRO.getDistance();
        this.globalTop = centerTalkRO.getGlobalTop();
        this.threeAppName = centerTalkRO.getThreeAppName();
        this.threeMpQqAppId = centerTalkRO.getThreeMpQqAppId();
        this.threeMpWxAppId = centerTalkRO.getThreeMpWxAppId();
        this.threeAppId = centerTalkRO.getThreeAppId();
        this.threeId = centerTalkRO.getThreeId();
        this.threeTalkPath = centerTalkRO.getThreeTalkPath();
        this.threeContent = centerTalkRO.getThreeContent();
        this.visibleGender = centerTalkRO.getVisibleGender();
    }
}