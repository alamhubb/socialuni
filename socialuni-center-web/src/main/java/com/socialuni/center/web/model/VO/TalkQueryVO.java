package com.socialuni.center.web.model.VO;

import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
public class TalkQueryVO {
    private List<String> talkIds;
    private String homeType;
    private String tabType;
    private List<Integer> tagIds;

    private String adCode;
    private Double lon;
    private Double lat;

    private Integer minAge;
    private Integer maxAge;
    private String gender;
    //新版本用genderType，旧版本用gender
    private String genderType;
    private Boolean openPosition;
    //平台
    private String platform;
    //备用属性
    private String standby;
}
