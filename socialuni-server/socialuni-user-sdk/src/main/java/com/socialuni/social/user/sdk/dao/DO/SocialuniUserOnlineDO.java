package com.socialuni.social.user.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUpdateTimeContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Data
@Entity
@Table(name = "s_user_online",
        indexes = {
                @Index(columnList = "userId"),
        }
)
public class SocialuniUserOnlineDO extends SocialuniUpdateTimeContentBaseDO {

    //在线分钟数
    Integer onlineMinute;
    //    Date offLineTime;
    public SocialuniUserOnlineDO() {
        this.onlineMinute = 1;
    }
}
