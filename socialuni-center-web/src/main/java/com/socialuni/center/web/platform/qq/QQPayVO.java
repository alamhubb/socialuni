package com.socialuni.center.web.platform.qq;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-01-02 15:47
 */
@Data
public class QQPayVO {
    //应用ID
    private  String appid;
    //商户号
    private  String mch_id;
    //随机字符串
    private  String nonce_str;
    //签名
    private  String sign;
    //商品描述
    private  String body;
    //附加数据
    private  String attach;
    //商户订单号
    private  String out_trade_no;
    //货币类型定义
    private  String fee_type;
    //订单金额
    private  Integer total_fee;
    //终端IP
    private  String spbill_create_ip;
    //订单生成时间
    private  String time_start;
    //订单超时时间
    private  String time_expire;
    //支付方式限制
    private  String limit_pay;
    //代扣签约序列号
    private  String contract_code;
    //QQ钱包活动标识
    private  String promotion_tag;
    //支付场景
    private  String trade_type;
    //支付结果通知地址
    private  String notify_url;
    //设备号
    private  String device_info;
    //小程序跳转地址和参数
    private  String mini_app_param;


}
