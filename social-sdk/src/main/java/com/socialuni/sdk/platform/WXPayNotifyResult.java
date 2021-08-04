package com.socialuni.sdk.platform;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author qinkaiyuan
 * @date 2020-02-28 14:49
 */
@Data
@XmlRootElement(name = "xml")
public class WXPayNotifyResult {
    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String sign_type;
    private String result_code;
    private String err_code;
    private String err_code_des;
    private String openid;
    private String is_subscribe;
    private String trade_type;
    private String bank_type;
    private Integer total_fee;
    private Integer settlement_total_fee;
    private String fee_type;
    private Integer cash_fee;
    private String cash_fee_type;
    private Integer coupon_fee;
    private Integer coupon_count;
    private String transaction_id;
    private String out_trade_no;
    private String attach;
    private String time_end;

    private String coupon_type;
    private String coupon_id;
    private String coupon_type_0;
    private String coupon_id_0;
    private Integer coupon_fee_0;
    private String coupon_type_1;
    private String coupon_id_1;
    private Integer coupon_fee_1;
    private String coupon_type_2;
    private String coupon_id_2;
    private Integer coupon_fee_2;

    private String return_code;
    private String return_msg;
}
