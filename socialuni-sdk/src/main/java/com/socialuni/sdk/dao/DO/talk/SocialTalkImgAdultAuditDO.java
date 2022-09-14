package com.socialuni.sdk.dao.DO.talk;

import com.socialuni.sdk.dao.DO.SocialContentBaseDO;
import com.socialuni.sdk.dao.DO.base.SocialImgBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_talk_img_adult_audit",
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentId"})
        }
)
@Data
@NoArgsConstructor
public class SocialTalkImgAdultAuditDO extends SocialContentBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer contentUid;

    public SocialTalkImgAdultAuditDO(Integer contentUid) {
        this.contentUid = contentUid;
    }
}
