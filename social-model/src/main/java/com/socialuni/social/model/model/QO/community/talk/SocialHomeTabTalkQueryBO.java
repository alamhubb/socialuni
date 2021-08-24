package com.socialuni.social.model.model.QO.community.talk;

import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
public class SocialHomeTabTalkQueryBO {
    private List<Integer> talkIds;
    private String homeTabType;
    private List<Integer> tagIds;
    //新版本用genderType，旧版本用gender

    private String adCode;
    private Double lon;
    private Double lat;

    private Integer minAge;
    private Integer maxAge;

    private String talkUserGender;
    private String talkVisibleGender;
}
