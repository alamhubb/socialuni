package com.socialuni.center.web.model.QO.community.talk;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
public class SocialHomeTabTalkQueryQO {
    private List<Integer> talkIds;
    private String homeTabType;
    private List<Integer> tagIds;
    private List<String> tagNames;
    //新版本用genderType，旧版本用gender
    private String gender;

    private String adCode;
    private Double lon;
    private Double lat;

    private Integer minAge;
    private Integer maxAge;
    private Date queryTime;
    private String userGender;
    private String talkVisibleGender;
    private String circleName;
    //排除未实名认证的图片内容
    private Boolean hasPeopleImgTalkNeedIdentity;
}
