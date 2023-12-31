package com.socialuni.social.user.sdk.model.QO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SocialUserImgDeleteQO {
    @NotNull
    private Integer userImgId;

    public SocialUserImgDeleteQO(Integer userImgId) {
        this.userImgId = userImgId;
    }
}
