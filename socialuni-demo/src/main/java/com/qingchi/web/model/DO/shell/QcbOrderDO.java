package com.qingchi.web.model.DO.shell;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "qcb_order")
public class QcbOrderDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //同时为订单编号
    private Integer id;

    //没有这行 级联保存不生效
    private Integer userId;

    private Date createDate;

    private Integer qcbNum;

    //充值方式，微信，支付宝，开始仅仅支持微信
    private String payType;
    //充值才有，本次开通花了多少钱
    private Integer money;
    //是否参加活动
    private Boolean takeSale;
    //参与的活动
    private Integer vipSaleId;
    //邀请码，系统赠送才需要,一般是自己账号的注册码，基于哪个注册码，来的这个会员
    private String inviteCode;
    //别人谁注册后赠送给你的用户id
    private Integer inviteUserId;

    //外部系统订单编号，比如支付宝订单号，微信订单号
    private String outOrderNo;

    //获取类型，是充值获取还是赠送
    private String activityType;
}