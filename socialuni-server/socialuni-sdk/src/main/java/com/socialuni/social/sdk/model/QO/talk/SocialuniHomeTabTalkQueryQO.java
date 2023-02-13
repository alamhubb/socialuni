package com.socialuni.social.sdk.model.QO.talk;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
public class SocialuniHomeTabTalkQueryQO {

//    private Integer pageNum;

//    private List<Integer> tagIds;
//    private Date queryDate;
//    private String userGender;
    //新版本用genderType，旧版本用gender
//    private String talkVisibleGender;
    //排除未实名认证的图片内容， 由应用控制，不由前端控制
//    private Boolean hasPeopleImgTalkNeedIdentity;

    //兼容旧版本
    private String homeTabType;
    private String circleName;
    private List<String> talkIds;
    //是否首次加载
    private Boolean firstLoad;

    //首页tab名称
    private String homeTabName;
    // 筛选的用户行呗
    private String gender;
    // 筛选用户年龄
    private Integer minAge;
    private Integer maxAge;
    // 筛选的城市
    private String adCode;
    // 记录用户地理位置，计算距离
    private Double lon;
    private Double lat;
    // 记录查询时间，用来分页
    private Date queryTime;

    // 筛选的话题列表
    private List<String> tagNames;
    //仅查询设置了实名认证的用户的照片
//    private Boolean hasPeopleImgTalkNeedIdentity;
    //仅查询设置了学校信息的用户动态
//    private Boolean userHasSchoolNam;
}
