package com.qingchi.web.model.DO.shell;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vip_sale")
@Data
public class VipSaleDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //活动创建时间
    private Date createDate;
    //活动结束时间
    private Date endDate;
    //可用和过期
    private Boolean enabled;
    //多少月的活动
    private Integer monthNum;
    //充值还是系统赠送，是充值促销活动，还是免费赠送活动
    private String activityType;
    //几折
    private Integer discount;
    //活动描述
    private String description;
    //赠送类型，邀请人赠送，还是注册赠送，还是充值赠送
    private String giftType;
}
