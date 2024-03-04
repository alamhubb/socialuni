package com.socialuni.social.common.sdk.model.VO;

import lombok.Data;

import java.util.List;

@Data
public class TalkAddVO {
    private String content;
    private List<TalkImgVO> imgs;
    private String adCode;
    private String visibleType;
    private String visibleGender;
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