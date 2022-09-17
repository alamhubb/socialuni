package com.socialuni.sdk.model.QO.community.talk;

import lombok.Data;


@Data
public class SocialTalkImgAddQO {
    private String src;
    private Double aspectRatio;
    private Integer size;
    //压缩率
    private Double quality;
    private String content;
    private Boolean hasQrCode;
}
