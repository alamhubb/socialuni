package com.socialuni.center.web.model.DO.circle;

import com.socialuni.center.web.model.DO.base.CommonBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
//@Entity
@Table(name = "s_circle_campaign_vote_record", indexes = {
        @Index(columnList = "circleId"),
        @Index(columnList = "userId"),
        @Index(columnList = "period")
})
@Data
@NoArgsConstructor
public class SocialCircleCampaignVoteRecordDO extends CommonBaseDO implements Serializable {
    Integer circleId;
    Integer userId;
    Integer circleCampaignId;
    Integer electionUserId;
    Integer period;
}
