package com.socialuni.social.model.model.QO.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OAuthUserInfoQO {
    @NotBlank
    private String appId;
    @NotBlank
    private String mpType;

    public OAuthUserInfoQO() {
    }

    public OAuthUserInfoQO(String appId, String mpType) {
        this.appId = appId;
        this.mpType = mpType;
    }
}
