package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_community_talk_has_underage_img_audit",
        indexes = {
                @Index(columnList = "status")
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = "talkId")
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
//为什么单独做一个表，因为用talk的记录不知道哪条审核过哪条没审核过
public class SocialuniTalkHasUnderageImgAuditDO extends SocialuniUnionContentBaseDO implements Serializable {
    @Column(nullable = false)
    private Long talkId;
    @Column(nullable = false)
    private Long userId;

    public SocialuniTalkHasUnderageImgAuditDO(Long talkId, Long userId) {
        this.setStatus(ContentStatus.preAudit);
        this.setDevId(DevAccountFacade.getDevIdNullElseCenterDevId());
        this.talkId = talkId;
        this.userId = userId;
    }
}
