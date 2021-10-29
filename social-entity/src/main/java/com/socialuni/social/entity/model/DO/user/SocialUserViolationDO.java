package com.socialuni.social.entity.model.DO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "s_user_violation",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId")
        }
)
@Data
@NoArgsConstructor
public class SocialUserViolationDO implements Serializable {
    //在talk里面展示的放在user里？
    //必有
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer userId;

    /**
     * 被违规的次数，默认0
     */
    private Integer violationCount;

    /**
     * 违规原因
     */
    private String violationReason;
    /**
     * 封禁开始时间
     */
    private Date violationStartTime;

    /**
     * 封禁结束时间
     */
    private Date violationEndTime;

    public SocialUserViolationDO(UserDO user) {
        this.userId = user.getId();
        this.violationCount = 0;
    }
}
