package com.socialuni.social.community.sdk.model;

import lombok.Data;


@Data
public class TalkImgVO {
    private String src;
    private Double aspectRatio;
    private Integer size;
    //压缩率
    private Double quality;
}
