package com.socialuni.center.web.model.RO;

import com.socialuni.center.web.model.RO.user.base.SocialUserRO;
import lombok.Data;

@Data
public class ApplySocialUniOAuthRO {
    private String token;
    private SocialUserRO user;
}
