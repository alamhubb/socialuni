package com.socialuni.social.common.sdk.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniImgAddQO {
    @NotBlank
    private String src;
    private Double aspectRatio;
    //压缩率
    private Double quality;
    private Integer size;
    private String content;
    private Boolean hasQrCode;
//    private Boolean hasPeopleImg;
}
