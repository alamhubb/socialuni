package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
//@Entity
@Table(name = "s_community_circle_campaign_record", indexes = {
        @Index(columnList = "circleId"),
        @Index(columnList = "userId"),
        @Index(columnList = "period")
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialCircleCampaignRecordDO extends SocialuniContentBaseDO implements Serializable {
    Integer circleId;
    Long userId;
    //第几期
    Integer period;
    String manifesto;
    //获取的票数
    Integer votes;
    //支持率
    Double popularity;

}
