package com.socialuni.sdk.model.QO.community.talk;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
public class SocialHomeTabTalkQueryBO {
    //兼容旧版本
    private List<String> talkIds;

    private List<Integer> tagIds;
    //新版本用genderType，旧版本用gender

    private String adCode;
    private Double lon;
    private Double lat;

    private Integer minAge;
    //    private Integer pageNum;
    private Integer maxAge;
    private Date queryTime;

    private String talkUserGender;
    private String talkVisibleGender;
    private Integer devId;
    //查询圈子内容，需要设置circleid
    private Integer circleId;
    //仅查询成年审核通过的照片动态
//    private Boolean hasPeopleImgNeedIdentity;
    private Boolean userHasSchoolNam;

    //首页tab名称
    private String homeTabName;
}
