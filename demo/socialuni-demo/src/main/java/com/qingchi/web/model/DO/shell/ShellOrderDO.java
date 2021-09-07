package com.qingchi.web.model.DO.shell;

import com.qingchi.web.constant.ExpenseType;
import com.qingchi.web.constant.ShellOrderType;
import com.socialuni.social.constant.CommonStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data@Table(name = "shell_order")@Entity
public class ShellOrderDO implements Serializable {
    //必有
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    //本单贝壳数量，可能是正值，也可能是负值
    private Integer shell;
    //绝对值，
    private Integer absoluteShell;

    private Date createTime;
    private Date updateTime;

    //用户状态，暂未使用正常，封禁
    private String status;

    private String type;

    private Integer relatedOrderId;

    private Integer vipOrderId;

    private Integer userContactId;
    //消费类型，开启会话，获取联系方式
    private String expenseType;
    //备注
    private String expenseDetailType;

    public ShellOrderDO() {
    }

    public ShellOrderDO(Integer userId, Integer shell, String type, String expenseType) {
        this.userId = userId;
        this.shell = shell;
        this.absoluteShell = Math.abs(shell);
        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.status = CommonStatus.enable;
        this.type = type;
        this.expenseType = expenseType;
        this.expenseDetailType = expenseType;
    }

    public ShellOrderDO(Integer userId, Integer shell, String type, String expenseType, String expenseDetailType, Integer userContactId) {
        this(userId, shell, type, expenseType);
        this.expenseDetailType = expenseDetailType;
        this.userContactId = userContactId;
    }

    //被赠予shell调用的
    public ShellOrderDO(Integer userId, Integer shell, String expenseType, String expenseDetailType, Integer userContactId, Integer relatedOrderId) {
        this(userId, shell, ShellOrderType.receive, expenseType, expenseDetailType, userContactId);
        this.relatedOrderId = relatedOrderId;
// todo       relatedOrder.relatedOrder = this;
    }

    //充值shell调用的
    public ShellOrderDO(Integer userId, Integer shell, Integer vipOrderId) {
        this(userId, shell, ShellOrderType.recharge, ExpenseType.rechargeShell);
        this.vipOrderId = vipOrderId;
    }
}