package com.socialuni.center.web.platform;

import com.socialuni.center.web.model.DO.user.SocialTokenDO;
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
