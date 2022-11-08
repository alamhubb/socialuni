package com.socialuni.social.user.sdk.model.QO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SocialuniUserIdQO {
    @NotNull
    private String userId;
}