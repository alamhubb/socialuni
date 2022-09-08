package com.socialuni.sdk.dao.DO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_user_fans_detail",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId")
        }
)
@Data
@NoArgsConstructor
public class SocialUserFansDetailDO implements Serializable {
    //在talk里面展示的放在user里？
    //必有
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer fansNum;
    @Column(nullable = false)
    private Integer followNum;

    public SocialUserFansDetailDO(SocialuniUserDO user) {
        this.userId = user.getUnionId();
        this.fansNum = 0;
        this.followNum = 0;
    }
}
