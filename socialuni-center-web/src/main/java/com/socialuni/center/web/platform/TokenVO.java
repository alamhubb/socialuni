package com.socialuni.center.web.platform;

import com.socialuni.social.entity.model.DO.user.TokenDO;
import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-19 22:27
 */
@Data
public class TokenVO {
    private String tokenCode;

    public TokenVO(TokenDO tokenDO) {
        this.tokenCode = tokenDO.getToken();
    }
}
