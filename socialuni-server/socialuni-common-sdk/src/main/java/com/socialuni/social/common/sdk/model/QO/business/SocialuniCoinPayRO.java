package com.socialuni.social.common.sdk.model.QO.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 用户支付结果vo
 *
 * @author qinkaiyuan
 * @since 1.0.0
 */
@Data
public class SocialuniCoinPayRO {
    private String prepayid;
    @JsonProperty("package")
    private String package_alias;
    private String mweb_url;
    private String nonceStr;
    private String timeStamp;
    private String paySign;
}