package com.socialuni.sdk.model.RO;

import com.socialuni.sdk.model.RO.user.base.SocialUserRO;
import lombok.Data;

@Data
public class ApplySocialUniOAuthRO {
    private String token;
    private SocialUserRO user;
}
