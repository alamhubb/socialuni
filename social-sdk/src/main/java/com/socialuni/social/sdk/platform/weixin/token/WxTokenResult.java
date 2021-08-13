package com.socialuni.social.sdk.platform.weixin.token;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data@Component
public class WxTokenResult {
    private String access_token;
    private Date createTime;
    private Date expiresTime;
    private Integer expires_in;
    private Integer errcode;
    private String errmsg;

    public boolean tokenIsValid() {
        //token不为null，且未超时
        return StringUtils.isNotEmpty(access_token) && this.expiresTime.getTime() > new Date().getTime();
    }


    public boolean hasError() {
        return this.errcode != null && this.errcode != 0;
    }
}
