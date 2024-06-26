package com.socialuni.social.common.sdk.platform.weixin;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 16:25
 */
@Data
public class HttpResult {
    private Integer errcode;
    private String errmsg;
    public boolean hasError() {
        return this.errcode != null && this.errcode != 0;
    }
}
