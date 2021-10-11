package com.socialuni.social.sdk.platform.weixin.login;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class LoginResult {
    private String openid;
    private String client_id;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;

    public boolean hasError() {
        return this.errcode != null && this.errcode != 0;
    }
}
