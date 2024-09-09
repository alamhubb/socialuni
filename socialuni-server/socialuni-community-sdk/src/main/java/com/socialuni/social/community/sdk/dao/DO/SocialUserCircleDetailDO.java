package com.socialuni.social.community.sdk.dao.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class SocialUserCircleDetailDO extends SocialuniContentBaseDO implements Serializable {
    //在talk里面展示的放在user里？
    //必有
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Integer circleId;
}
