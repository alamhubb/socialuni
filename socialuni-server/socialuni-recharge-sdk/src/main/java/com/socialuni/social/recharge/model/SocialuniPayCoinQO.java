package com.socialuni.social.recharge.model;

import lombok.Data;

@Data
public class SocialuniPayCoinQO {
    //充值金额
    private Integer amount;
    //支付方式，微信qq
    private String provider;
}
