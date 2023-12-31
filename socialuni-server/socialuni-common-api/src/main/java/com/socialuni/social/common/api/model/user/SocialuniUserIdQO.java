package com.socialuni.social.common.api.model.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SocialuniUserIdQO {
    @NotNull
    String userId;
}
