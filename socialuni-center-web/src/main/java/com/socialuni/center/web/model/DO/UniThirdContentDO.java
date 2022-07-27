package com.socialuni.center.web.model.DO;

import com.socialuni.api.model.RO.SocialuniUidRO;
import com.socialuni.social.entity.model.DO.base.CommonBaseDO;
import com.socialuni.social.entity.model.DO.base.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "u_third_content",
        indexes = {
                @Index(columnList = "devId"),
                @Index(columnList = "thirdId"),
                @Index(columnList = "contentType"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentId", "contentType"}),
                @UniqueConstraint(columnNames = {"devId", "thirdId", "contentType"}),
        }
)
@Data
@NoArgsConstructor
public class UniThirdContentDO extends CommonBaseDO {
    @Column(nullable = false, updatable = false)
    private Integer devId;
    @Column(nullable = false, updatable = false)
    private String contentType;
    @Column(nullable = false, updatable = false)
    private String thirdId;
    @Column(nullable = false, updatable = false)
    private String contentId;

    public UniThirdContentDO(Integer devId, String contentType, String thirdId, String contentId) {
        this.devId = devId;
        this.thirdId = thirdId;
        this.contentId = contentId;
        this.contentType = contentType;
    }
}
