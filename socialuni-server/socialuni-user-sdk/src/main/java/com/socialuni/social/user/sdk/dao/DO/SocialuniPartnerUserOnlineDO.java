package com.socialuni.social.user.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUpdateTimeContentBaseDO;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "s_partner_user_online",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "todayDate"),
                @Index(columnList = "createTime"),
        }
)
public class SocialuniPartnerUserOnlineDO extends SocialuniUpdateTimeContentBaseDO {
    @Column(nullable = false, updatable = false)
    String todayDate;

    //在线分钟数
    @Column(nullable = false)
    Integer onlineMinute;

    //    Date offLineTime;
    public SocialuniPartnerUserOnlineDO() {
        this.onlineMinute = 1;
        Date curTime = new Date();
        String curDate = SocialuniDateUtils.simpleDateFormat.format(curTime);
        this.todayDate = curDate;
    }
}
