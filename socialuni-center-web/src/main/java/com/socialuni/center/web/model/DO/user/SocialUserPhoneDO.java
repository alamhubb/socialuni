package com.socialuni.center.web.model.DO.user;

import com.socialuni.center.web.model.DO.base.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


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
@NoArgsConstructor
public class SocialUserPhoneDO extends CommonContentBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private Integer userId;
    @Column(nullable = false)
    private String phoneCountryCode;
    private String phoneNum;
    @Column(nullable = false)
    private Integer devId;

    public SocialUserPhoneDO(Integer userId, String phoneCountryCode, String phoneNum, Integer devId) {
        this.userId = userId;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneNum = phoneNum;
        this.devId = devId;
    }
}
