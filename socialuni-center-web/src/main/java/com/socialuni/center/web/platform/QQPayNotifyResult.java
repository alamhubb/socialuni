package com.socialuni.center.web.platform;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author qinkaiyuan
 * @date 2020-02-28 14:49
 */
@Data
@XmlRootElement(name = "xml")
public class QQPayNotifyResult {
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String device_info;
    private String trade_type;
    private String trade_state;
    private String bank_type;
    private String fee_type;
    private Integer total_fee;
    private Integer cash_fee;
    private Integer coupon_fee;
    private String transaction_id;
    private String out_trade_no;
    private String attach;
    private String time_end;
    private String openid;
}
