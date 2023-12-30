package com.socialuni.social.common.sdk.platform.baidu;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaiduLoginResult {
    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;

    public boolean hasError() {
        return this.errcode != null && this.errcode != 0;
    }
}
