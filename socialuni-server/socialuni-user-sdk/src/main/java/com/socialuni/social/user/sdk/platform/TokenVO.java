package com.socialuni.social.user.sdk.platform;

import com.socialuni.social.user.sdk.model.DO.SocialTokenDO;
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
