package com.socialuni.social.user.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "s_user_password",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "devId"),
                @Index(columnList = "account"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"devId", "userId"})
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialUserPasswordDO extends SocialuniContentBaseDO {
    @Column(nullable = false, updatable = false)
    private Long userId;
    @Column(nullable = false)
    private Integer devId;
    @Column(nullable = false)
    private String account;
    @Column(nullable = false)
    private String password;
    //3小时3次，错误三次，则3小时候再重试，如忘记密码请修改密码
    @Column(nullable = false)
    private Integer errorNum;
    @Column(nullable = false)
    private Date lastErrorTime;
}
