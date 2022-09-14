package com.socialuni.sdk.model.QO.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SocialuniUserIdQO {
    @NotNull
    private String userId;
}