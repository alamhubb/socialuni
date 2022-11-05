package com.socialuni.social.sdk.logic.platform;

import com.socialuni.social.user.sdk.entity.SocialTokenDO;
import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-19 22:27
 */
@Data
public class TokenVO {
    private String tokenCode;

    public TokenVO(SocialTokenDO tokenDO) {
        this.tokenCode = tokenDO.getToken();
    }
}
