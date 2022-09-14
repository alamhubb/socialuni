package com.socialuni.sdk.dao.DO.talk;

import com.socialuni.sdk.dao.DO.SocialContentBaseDO;
import com.socialuni.sdk.dao.DO.base.SocialImgBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_talk_adult_audit",
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentId"})
        }
)
@Data
@NoArgsConstructor
public class SocialTalkAdultAuditDO extends SocialContentBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer contentUid;

    public SocialTalkAdultAuditDO(Integer contentUid) {
        this.contentUid = contentUid;
    }
}
