package com.socialuni.social.user.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "s_user_phone",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "phoneNum"),
                @Index(columnList = "status"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"devId", "phoneNum"})
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialUserPhoneDo extends SocialuniContentBaseDO {
    @Column(nullable = false, updatable = false)
    private Integer userId;
    @Column(nullable = false)
    private String phoneCountryCode;
    private String phoneNum;
    @Column(nullable = false)
    private Integer devId;

    public SocialUserPhoneDo(Integer userId, String phoneCountryCode, String phoneNum, Integer devId) {
        this.userId = userId;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneNum = phoneNum;
        this.devId = devId;
    }
}
