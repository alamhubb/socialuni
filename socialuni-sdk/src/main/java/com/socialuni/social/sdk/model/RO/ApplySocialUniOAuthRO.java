package com.socialuni.social.sdk.model.RO;

import com.socialuni.social.sdk.model.RO.user.SocialuniUserRO;
import lombok.Data;

@Data
public class ApplySocialUniOAuthRO {
    private String token;
    private SocialuniUserRO user;
}
