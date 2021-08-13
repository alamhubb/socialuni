package com.socialuni.social.entity.model.DO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_fans_detail",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId")
        }
)
@Data
@NoArgsConstructor
public class SocialUserFansDetailDO {
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

    public SocialUserFansDetailDO(UserDO user) {
        this.userId = user.getId();
        this.fansNum = 0;
        this.followNum = 0;
    }
}
