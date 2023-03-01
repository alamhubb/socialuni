package com.socialuni.social.sdk.dao.DO.bussiness;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//金币充值订单
@Data
@Entity
@Table(name = "s_coin_recharge_order")
public class QcbOrderDO extends SocialuniContentBaseDO implements Serializable {
    private Integer qcbNum;

    //充值方式，微信，支付宝，开始仅仅支持微信
    private String payType;
    //充值才有，本次开通花了多少钱
    private Integer money;
    //外部系统订单编号，比如支付宝订单号，微信订单号
    private String outSysOrderNo;
}