package com.socialuni.social.common.sdk.dao.DO;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "s_user",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "userId"),
                @Index(columnList = "updateTime"),
                @Index(columnList = "gender"),
                //有时候根据名字查询
                @Index(columnList = "nickname"),
                @Index(columnList = "age"),
                @Index(columnList = "type"),
                @Index(columnList = "roleId"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniUserDo extends SocialuniUnionContentBaseDO {
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private Integer age;
    private String city;
    private String type;
    private String roleId;

    //获取userId的正确方式为getUserId，这个id不正确
    @Deprecated
    public Integer getId() {
        return super.getId();
    }

    public SocialuniUserDo(Long userId) {
        super(userId, userId, SocialuniContentType.user, null);
    }
}