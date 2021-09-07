package com.qingchi.web.model.RO;

import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRO extends SocialLoginRO<MineUserDetailRO> {
    //兼容旧版本
    @Deprecated
    String tokenCode;

    public LoginRO(String token, MineUserDetailRO user) {
        super(token, user);
        this.tokenCode = token;
    }
}
