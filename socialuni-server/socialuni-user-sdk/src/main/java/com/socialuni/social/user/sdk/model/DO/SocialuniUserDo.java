package com.socialuni.social.user.sdk.model.DO;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import lombok.Data;
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
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
@Data
@NoArgsConstructor
public class SocialuniUserDo extends SocialuniUnionContentBaseDO {
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private Integer age;
    private String city;
    private String type;

    public SocialuniUserDo(Integer userId) {
        super(userId, SocialuniContentType.user, null);
    }
}