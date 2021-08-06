package com.socialuni.social.model.model.QO.community.talk;

import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
public class SocialHomeTabTalkQueryQO {
    private List<Integer> talkIds;
    private String tabType;
    private List<Integer> tagIds;

    private String adCode;
    private Double lon;
    private Double lat;

    private Integer minAge;
    private Integer maxAge;
    private String userGender;
    //新版本用genderType，旧版本用gender
    private String talkVisibleGender;
    //新版本用genderType，旧版本用gender
    private String genderType;
    private Boolean openPosition;
    //平台
    private String platform;
    //备用属性
    private String standby;
}
