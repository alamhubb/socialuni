package com.socialuni.social.sdk.model.QO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetOAuthUserInfoQO {
    //三方用户id
//    private String appUserId;
    @NotBlank
    private String token;
    //platform目前支持mp


    public GetOAuthUserInfoQO() {
    }

    public GetOAuthUserInfoQO(String token) {
        this.token = token;
    }
}
