package com.socialuni.social.user.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "s_user_fans_detail",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId")
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialUserFansDetailDo extends SocialuniUserInfoBaseDO {
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

    public SocialUserFansDetailDo(SocialuniUserDo user) {
        this.userId = user.getUnionId();
        this.fansNum = 0;
        this.followNum = 0;
    }
}
