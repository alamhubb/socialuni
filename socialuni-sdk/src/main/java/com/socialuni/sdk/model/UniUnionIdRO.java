package com.socialuni.sdk.model;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class UniUnionIdRO {
    private String openid;
    private String client_id;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;

    //兼容qq和微信
    public boolean hasError() {
        return this.errcode != null && this.errcode != 0;
    }
}
