package com.socialuni.social.common.sdk.platform.qq;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 16:25
 */
@Data
//@JacksonXmlRootElement(localName = "xml")
public class QQPayResult {
    private String return_code;
    private String return_msg;
    private String retcode;
    private String retmsg;
    private String appid;
    private String mch_id;
    private String sign;
    private String result_code;
    private String err_code;
    private String err_code_des;
    private String nonce_str;
    private String trade_type;
    private String prepay_id;
    private String code_url;
    private String mweb_url;

    public boolean hasError() {
        return "FAIL".equals(return_code);
    }
}
