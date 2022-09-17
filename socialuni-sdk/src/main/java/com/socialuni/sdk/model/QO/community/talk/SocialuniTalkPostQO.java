package com.socialuni.sdk.model.QO.community.talk;

import com.socialuni.sdk.model.QO.ContentAddQO;
import lombok.Data;

import java.util.List;

@Data
public class SocialuniTalkPostQO extends ContentAddQO {
    private List<SocialTalkImgAddQO> imgs;
    private String adCode;
    private String visibleType;
    private String visibleGender;
//    private Integer devId;
//    private String socialuniUid;
    private List<Integer> tagIds;
    private List<String> tagNames;
    private String circleName;
    /*
     *  经度 Longitude 简写Lng
     * 纬度范围-90~90，经度范围-180~180
     */
    private Double lon;
    /*
     * 纬度 Latitude 简写Lat
     */
    private Double lat;
}