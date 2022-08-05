package com.socialuni.center.web.model.DO;

import com.socialuni.center.web.model.DO.base.CommonBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "u_user_account",
        indexes = {
                @Index(columnList = "devId"),
                @Index(columnList = "userId")
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"devId", "thirdUserId"}),
        }
)
@Data
@NoArgsConstructor
public class UniUserAccountDO extends CommonBaseDO {
    @Column(nullable = false, updatable = false)
    private Integer devId;
    @Column(nullable = false, updatable = false)
    private Integer thirdUserId;
    @Column(nullable = false, updatable = false)
    private Integer userId;

    public UniUserAccountDO(Integer devId, Integer thirdUserId, Integer userId) {
        this.devId = devId;
        this.thirdUserId = thirdUserId;
        this.userId = userId;
    }
}
