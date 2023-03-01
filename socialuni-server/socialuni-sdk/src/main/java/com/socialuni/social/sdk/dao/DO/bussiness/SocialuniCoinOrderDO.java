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
    private Integer shell;
    //绝对值，
    private Integer absoluteShell;

    private String type;

    private Integer rechargeOrderId;

    private Integer userContactId;

    public SocialuniCoinOrderDO() {
    }

    public SocialuniCoinOrderDO(Integer userId, Integer shell, String type) {
        super(userId);
        this.shell = shell;
        this.absoluteShell = Math.abs(shell);
        this.type = type;
    }

    public SocialuniCoinOrderDO(Integer userId, Integer shell, String type, Integer userContactId) {
        super(userId);
        this.shell = shell;
        this.absoluteShell = Math.abs(shell);
        this.type = type;
        this.userContactId = userContactId;
    }


    /*public ShellOrderDO(Integer userId, Integer shell, Integer vipOrderId) {
        this(userId, shell, ShellOrderType.recharge);
        this.vipOrderId = vipOrderId;
    }*/
}