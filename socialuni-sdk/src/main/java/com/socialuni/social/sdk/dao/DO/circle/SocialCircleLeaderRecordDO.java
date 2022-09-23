package com.socialuni.social.sdk.dao.DO.circle;

import com.socialuni.social.sdk.dao.DO.base.CommonContentBaseDO;
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
@Table(name = "s_circle_leader_record", indexes = {
        @Index(columnList = "circleId"),
        @Index(columnList = "userId"),
        @Index(columnList = "period")
})
@Data
@NoArgsConstructor
public class SocialCircleLeaderRecordDO extends CommonContentBaseDO implements Serializable {
    Integer circleId;
    Integer userId;
    //第几期
    Integer period;
    Integer circleCampaignId;
    //证明你是第几期的圈主，基于哪个竞选当选的。
}
