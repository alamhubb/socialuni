package com.socialuni.social.model.model.RO.user.login;

import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialLoginRO {
    String token;
    SocialMineUserDetailRO user;

    public SocialLoginRO(String token, SocialMineUserDetailRO user) {
        this.token = token;
        this.user = user;
    }
}
