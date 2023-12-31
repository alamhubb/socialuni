package com.socialuni.social.sdk.dao.DO.bussiness;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

//金币充值订单
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "s_business_pay_coin_order")
public class SocialuniPayCoinOrderDO extends SocialuniContentBaseDO implements Serializable {
    private String orderNo;
    private Integer coinNum;

//    private String payType;

    //充值才有，本次开通花了多少钱
    private Integer amount;
    //外部系统订单编号，比如支付宝订单号，微信订单号
    private String outSysOrderNo;
    private String outSysSign;
    //充值方式，微信，支付宝，开始仅仅支持微信
    private String payType;
    //使用平台
    private String provider;
    private String useSystem;
    private String platform;
}