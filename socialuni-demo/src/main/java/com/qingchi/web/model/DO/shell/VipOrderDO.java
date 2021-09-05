package com.qingchi.web.model.DO.shell;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vip_order",
        indexes = {@Index(columnList = "orderNo")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"orderNo"})}
)
@Data
public class VipOrderDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //同时为订单编号
    private Integer id;
    //没有这行 级联保存不生效
    private Integer userId;
    private Date createTime;
    //本次开始时间
    private Date startDate;
    //本次到期时间
    private Date endDate;
    /**
     * 订单状态
     */
    private String status;
    //充值才有，本次开通花了多少钱，单位分
    private Integer money;
    //邀请码，系统赠送才需要,一般是自己账号的注册码，基于哪个注册码，来的这个会员
    //外部系统订单编号，比如支付宝订单号，微信订单号
    private String outOrderNo;
    private String orderNo;
    private String channel;
    //购买产品类型，vip还是贝壳shell
    private String payType;
    //充值前金额，应该和money相等才对
    private Integer amount;
    private Integer shellOrderId;
    //支付平台
    private String platform;

    /*//获取类型，是充值获取还是赠送
    private String activityType;
    //是否已经过期
    private Boolean enabled;
    //订单完成时间
    private Date successTime;
    //会员开了多少个月
    private Integer monthNum;
    //加上之前的订单总共多少个月
    private Integer totalMonthNum;
    //是否是年会员
    private Boolean yearVipFlag;
    //是否为续费
    private Boolean renew;
    //基于哪个进行的续费，上一次的订单，续费才有
    @JoinColumn
    @OneToOne
    private VipOrderDO lastVipOrder;
    //充值方式，微信，支付宝，开始仅仅支持微信
    private String payType;
    private String inviteCode;
    //别人谁注册后赠送给你的用户id
    @JoinColumn
    @OneToOne
    private UserDO inviteUser;
    //是否参加活动
    private Boolean takeSale;
    @JoinColumn
    @OneToOne
    //参与的活动
    private VipSaleDO vipSale;
    */
}
