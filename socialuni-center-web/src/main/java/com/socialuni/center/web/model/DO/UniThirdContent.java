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
                @Index(columnList = "thirdId")
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentId", "contentType"}),
                @UniqueConstraint(columnNames = {"thirdId", "contentType"}),
        }
)
@Data
@NoArgsConstructor
public class UniThirdContent extends CommonBaseDO {
    @Column(nullable = false, updatable = false)
    private String thirdId;
    private Integer contentId;
    private String contentType;
}
