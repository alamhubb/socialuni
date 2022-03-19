package com.socialuni.center.web.model.DO;

import com.socialuni.social.entity.model.DO.base.CommonBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "u_dev_content",
        indexes = {
                @Index(columnList = "devId"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentId", "contentType"}),
        }
)
@Data
@NoArgsConstructor
public class DevContentDO extends CommonBaseDO {
    @Column(nullable = false, updatable = false)
    private Integer devId;
    @Column(nullable = false, updatable = false)
    private Integer contentId;
    @Column(nullable = false, updatable = false)
    private Integer contentType;

    public DevContentDO(Integer devId, Integer contentId, String contentType) {
        this.devId = devId;
        this.contentId = contentId;
    }
}
