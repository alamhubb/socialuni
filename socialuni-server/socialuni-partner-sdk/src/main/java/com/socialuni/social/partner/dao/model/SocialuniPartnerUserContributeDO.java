package com.socialuni.social.partner.dao.model;

import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s_partner_user_contribute",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "todayDate"),
        },
        uniqueConstraints = {
                //每个类型，每天只能有一次数据
                @UniqueConstraint(columnNames = {"userId", "todayDate"})
        })
//记录用户每日在线的贡献值
public class SocialuniPartnerUserContributeDO extends SocialuniUserInfoBaseDO {
    @Column(nullable = false)
    //今日日期
    String todayDate;
    //今日在线时长
    Integer onlineMinute;
    //今日贡献值
    Integer contributeValue;
    //不计算加成的贡献值
    Integer originalContributeValue;
    //使用倍数加成，
    Integer useMultiple;

    //占比都应该是最大4位数
    //贡献值
    Integer valuePercent;
    //不计算加成的分配占比
    Integer originalValuePercent;


    Integer assignPercent;
    Integer originalAssignPercent;

    //今日获得积分
    Integer originalAssignPoints;
    Integer assignPoints;

    //贡献排名
    Integer contributeRank;
    //原始贡献排名
    Integer originalContributeRank;


    //发送消息数量
    Integer sendMsgNum;

    //使用倍数加成，
    Integer payMultiple;


    //把离线时间设置为12点。如果超过12点，则重置，判断需要同一天的10分钟前。
    //倍数
    //今日在线时长
}
