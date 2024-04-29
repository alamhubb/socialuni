package com.socialuni.social.common.api.model.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SocialuniUserIdQO {
    @NotBlank(message = "入参为空异常")
    String userId;
}
