package com.socialuni.social.sdk.model.QO;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SocialuniImgAddQO {
    @NotBlank
    private String src;
    @NotNull
    private Double aspectRatio;
    //压缩率
    private Double quality;
    private Integer size;
    private String content;
    private Boolean hasQrCode;
//    private Boolean hasPeopleImg;
}
