package com.socialuni.social.model.model.QO.community.talk;

import com.socialuni.social.model.model.QO.ContentAddQO;
import lombok.Data;

import java.util.List;

@Data
public class SocialTalkPostQO implements ContentAddQO {
    private List<SocialTalkImgAddQO> imgs;
    private String adCode;
    private String visibleType;
    private String content;
    private String visibleGender;
    private String socialuniUid;
    private List<Integer> tagIds;
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