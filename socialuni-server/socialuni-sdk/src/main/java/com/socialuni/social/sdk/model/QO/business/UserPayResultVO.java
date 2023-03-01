package com.socialuni.social.sdk.model.QO.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 用户支付结果vo
 *
 * @author qinkaiyuan
 * @since 1.0.0
 */
@Data
public class UserPayResultVO {
    private String prepayid;
    @JsonProperty("package")
    private String package_alias;
    private String nonceStr;
    private String timeStamp;
    private String paySign;
}