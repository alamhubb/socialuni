package com.socialuni.social.partner.dao.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s_partner_points_main",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "consumeType"),
                @Index(columnList = "todayDate")
        },
        uniqueConstraints = {
                //每个类型，每天只能有一次数据
                @UniqueConstraint(columnNames = {"consumeType", "todayDate"})
        })
public class SocialuniPartnerPointsMainDO extends SocialuniUpdateTimeContentBaseDO {
    @Column(nullable = false)
    Integer balance;
    @Column(nullable = false)
    Integer consume;
    @Column(nullable = false)
    Integer tenThousandConsume;
    //销毁，分配，特殊用途，需要备注
    @Column(nullable = false)
    String consumeType;
    //今天日期，字符串类型，方便查询
    @Column(nullable = false)
    String todayDate;
    String remark;

    @Column(nullable = false)
    //已分配数量
    Integer assignNum;
    @Column(nullable = false)
    //销毁数量，未分配的则销魂，分配+销魂应该等于 consume
    Integer destroyNum;

    public SocialuniPartnerPointsMainDO() {
        this.assignNum = 0;
    }
}
