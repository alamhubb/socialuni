package com.socialuni.social.recharge.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "s_business_coin_order")
@Entity
@EqualsAndHashCode(callSuper = true)
public class SocialuniCoinOrderDO extends SocialuniContentBaseDO implements Serializable {
    //本单贝壳数量，可能是正值，也可能是负值
    @Column(nullable = false)
    private Integer shell;
    //绝对值，什么用，没想明白
    @Column(nullable = false)
    private Integer absoluteShell;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String detailType;

    private Integer relateId;

    @Column(nullable = false)
    private Integer userCoin;

    public SocialuniCoinOrderDO() {
    }

    public SocialuniCoinOrderDO(Integer userId, Integer shell, String type, String detailType, Integer userCoin,Integer relateId) {
        super(userId);
        this.shell = shell;
        this.absoluteShell = Math.abs(shell);
        this.type = type;
        this.detailType = detailType;
        this.relateId = relateId;
        this.userCoin = userCoin;
    }


    /*public ShellOrderDO(Integer userId, Integer shell, Integer vipOrderId) {
        this(userId, shell, ShellOrderType.recharge);
        this.vipOrderId = vipOrderId;
    }*/
}