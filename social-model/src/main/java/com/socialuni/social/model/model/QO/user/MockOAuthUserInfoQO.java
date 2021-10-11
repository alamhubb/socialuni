package com.socialuni.social.model.model.QO.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MockOAuthUserInfoQO {
    private String appId;
    private String mpType;

    public MockOAuthUserInfoQO() {
    }

    public MockOAuthUserInfoQO(String appId, String mpType) {
        this.appId = appId;
        this.mpType = mpType;
    }
}
