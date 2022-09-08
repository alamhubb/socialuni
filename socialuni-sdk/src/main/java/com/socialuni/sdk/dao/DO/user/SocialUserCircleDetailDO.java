package com.socialuni.sdk.dao.DO.user;

import com.socialuni.sdk.dao.DO.base.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_user_circile_detail",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId")
        }
)
@Data
@NoArgsConstructor
public class SocialUserCircleDetailDO extends CommonContentBaseDO implements Serializable {
    //在talk里面展示的放在user里？
    //必有
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer circleId;
}
