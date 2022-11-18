package com.socialuni.social.user.sdk.platform;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-03-14 0:29
 */
@Data
public class TokenDTO {
    public String accessToken;


    public TokenDTO() {
    }

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
