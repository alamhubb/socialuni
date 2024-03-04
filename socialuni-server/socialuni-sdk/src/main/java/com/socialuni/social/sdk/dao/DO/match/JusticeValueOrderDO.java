package com.socialuni.social.sdk.dao.DO.match;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2020-03-15 17:15
 */@Entity
@Table(name = "s_justice_value_order")
@Data
public class JusticeValueOrderDO implements Serializable {
    /**
     * 举报正确类别，奖励20，举报错误类别奖励10，举报失败扣50，
     * <p>
     * 审核过后，发放奖励
     * <p>
     * 如果已经被举报多少次不能再举报，举报超过多少先删除，talk状态要加一个审核中，2个人举报就被删，进入审核中，查询在审核中的和正常的
     * <p>
     * 审核完就变成正常和违规，发放奖励
     * <p>
     * 每个人每天只能举报10个，分值低于0的，每天只能举报两个，分值低于-200，不能再进行举报
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    /**
     * 正义分
     */
    private Integer justiceValue;
    //举报成功还是失败
    //是否获得了奖励
    //如果今天被奖励次数满了则不能讲理 通过10次奖励来判断
    private Date createTime;
    private Date updateTime;
    private Integer reportDetailId;
}
