package com.socialuni.social.sdk.dao.DO.circle;

import com.socialuni.social.common.repository.CommonContentBaseDO;
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
@Table(name = "s_circle_campaign_record", indexes = {
        @Index(columnList = "circleId"),
        @Index(columnList = "userId"),
        @Index(columnList = "period")
})
@Data
@NoArgsConstructor
public class SocialCircleCampaignRecordDO extends CommonContentBaseDO implements Serializable {
    Integer circleId;
    Integer userId;
    //第几期
    Integer period;
    String manifesto;
    //获取的票数
    Integer votes;
    //支持率
    Double popularity;

}
