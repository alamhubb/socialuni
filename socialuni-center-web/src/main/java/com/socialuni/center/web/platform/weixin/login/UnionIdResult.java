package com.socialuni.center.web.platform.weixin.login;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class UnionIdResult {
    private String openid;
    private String client_id;
    private String unionid;
    private Integer error;
    private String error_description;

    public boolean hasError() {
        return this.error != null && this.error != 0;
    }
}
