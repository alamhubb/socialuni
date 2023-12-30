package com.socialuni.social.common.sdk.platform.baidu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data@Component
public class BaiduTokenResult {
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String scope;
    private String session_key;
    private String session_secret;
    private Date createTime;
    private Date expiresTime;
    private Integer errcode;



    public boolean tokenIsValid() {
        //token不为null，且未超时
        return StringUtils.isNotEmpty(access_token) && this.expiresTime.getTime() > new Date().getTime();
    }


    public boolean hasError() {
        return this.errcode != null && this.errcode != 0;
    }
}
