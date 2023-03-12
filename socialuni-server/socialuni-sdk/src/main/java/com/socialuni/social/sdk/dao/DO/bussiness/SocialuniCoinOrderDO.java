package com.socialuni.social.sdk.dao.DO.bussiness;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "s_coin_order")
@Entity
public class SocialuniCoinOrderDO extends SocialuniContentBaseDO implements Serializable {
    //本单贝壳数量，可能是正值，也可能是负值
    @Column(nullable = false)
    private Integer shell;
    //绝对值，
    @Column(nullable = false)
    private Integer absoluteShell;

    @Column(nullable = false)
    private String type;

    private Integer rechargeOrderId;

    private Integer userContactId;
    @Column(nullable = false)
    private Integer userCoin;

    public SocialuniCoinOrderDO() {
    }

    public SocialuniCoinOrderDO(Integer userId, Integer shell, String type, Integer userCoin) {
        super(userId);
        this.shell = shell;
        this.absoluteShell = Math.abs(shell);
        this.type = type;
        this.userCoin = userCoin;
    }


    /*public ShellOrderDO(Integer userId, Integer shell, Integer vipOrderId) {
        this(userId, shell, ShellOrderType.recharge);
        this.vipOrderId = vipOrderId;
    }*/
}