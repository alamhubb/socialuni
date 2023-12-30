package com.socialuni.social.user.sdk.model.RO;

import com.socialuni.social.user.sdk.model.DO.SocialuniTokenDO;
import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-19 22:27
 */
@Data
public class TokenVO {
    private String tokenCode;

    public TokenVO(SocialuniTokenDO tokenDO) {
        this.tokenCode = tokenDO.getToken();
    }
}
