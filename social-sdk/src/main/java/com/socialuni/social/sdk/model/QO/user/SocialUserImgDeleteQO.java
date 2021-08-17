package com.socialuni.social.sdk.model.QO.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SocialUserImgDeleteQO {
    @NotNull
    private Integer userImgId;
}
