package com.socialuni.center.web.model.DO;

import com.socialuni.api.model.RO.SocialuniUidRO;
import com.socialuni.social.entity.model.DO.base.CommonBaseDO;
import com.socialuni.social.entity.model.DO.base.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "u_socialuni_uid",
        indexes = {
                @Index(columnList = "socialuniId")
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentId", "contentType"}),
        }
)
@Data
@NoArgsConstructor
public class SocialuniUidDO extends CommonBaseDO {
    @Column(nullable = false, updatable = false)
    private String socialuniUid;
    private String contentUid;

    public SocialuniUidDO(SocialuniUidRO appDO, SocialuniUidRO socialuniDO) {
        this.contentUid = appDO.getId();
        this.socialuniUid = socialuniDO.getId();
    }
}
