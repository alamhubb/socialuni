package com.socialuni.social.user.sdk.model;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserImgVO {
    private String id;
    @NotBlank
    private String src;
    @NotNull
    private Double aspectRatio;
    private Double height;
    private Double width;
    //压缩率
    private Double quality;
    private Integer size;
    private Integer reportNum;
}
