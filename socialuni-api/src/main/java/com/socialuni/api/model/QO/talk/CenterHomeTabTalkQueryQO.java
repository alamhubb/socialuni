package com.socialuni.api.model.QO.talk;

import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
public class CenterHomeTabTalkQueryQO {
    private List<String> talkIds;
    private String homeTabType;
    private List<Integer> tagIds;
    private String gender;

    private String adCode;
    private Double lon;
    private Double lat;

    private Integer minAge;
    private Integer maxAge;
    private String userGender;
    //新版本用genderType，旧版本用gender
    private String talkVisibleGender;
}
