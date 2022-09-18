package com.socialuni.sdk.dao.DO.community.talk;

import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.dao.DO.SocialContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_talk_has_underage_img_audit",
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
//为什么单独做一个表，因为用talk的记录不知道哪条审核过哪条没审核过
public class SocialuniTalkHasUnderageImgAuditDO extends SocialContentBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer talkId;

    public SocialuniTalkHasUnderageImgAuditDO(Integer talkId) {
        this.setStatus(ContentStatus.preAudit);
        this.talkId = talkId;
    }
}
