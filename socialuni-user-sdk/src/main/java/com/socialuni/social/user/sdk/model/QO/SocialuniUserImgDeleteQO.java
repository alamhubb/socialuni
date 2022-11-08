package com.socialuni.social.user.sdk.model.QO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SocialuniUserImgDeleteQO {
    @NotBlank
    private String userImgId;

    public SocialuniUserImgDeleteQO(String userImgId) {
        this.userImgId = userImgId;
    }
}
