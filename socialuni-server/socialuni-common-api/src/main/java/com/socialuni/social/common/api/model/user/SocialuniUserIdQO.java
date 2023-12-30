package com.socialuni.social.common.api.model.user;

import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SocialuniUserIdQO {
    @NotNull
    String userId;
}
