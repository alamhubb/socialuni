package com.socialuni.center.web.model.RO.user.login;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialLoginRO<T> {
    String token;
    T user;

    public SocialLoginRO(String token, T user) {
        this.token = token;
        this.user = user;
    }
}
