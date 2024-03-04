package com.socialuni.social.sdk.model.RO;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import lombok.Data;

@Data
public class ApplySocialUniOAuthRO {
    private String token;
    private SocialuniUserRO user;
}
