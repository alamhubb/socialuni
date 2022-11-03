package com.socialuni.social.user.sdk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocialuniImgBaseModel extends SocialuniUnionContentBaseModel implements Serializable {
    private String src;

    private Double aspectRatio;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;
}
