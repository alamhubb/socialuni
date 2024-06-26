package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
@Entity
@Table(name = "s_community_circle_period", indexes = {@Index(columnList = "circleId"), @Index(columnList = "period")})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialCirclePeriodDO extends SocialuniContentBaseDO implements Serializable {
    //第几期
    Integer circleId;
    Integer period;
    Integer nextPeriod;
    //本期结束时间
    Date endTime;
    //本期周期
    Integer periodDay;
    //本期当选者
    Integer circleCampaignId;
}
