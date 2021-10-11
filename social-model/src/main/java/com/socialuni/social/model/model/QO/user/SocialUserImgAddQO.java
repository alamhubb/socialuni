package com.socialuni.social.model.model.QO.user;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SocialUserImgAddQO {
    @NotBlank
    private String src;
    @NotNull
    private Double aspectRatio;
    //压缩率
    private Double quality;
    private Integer size;
}
