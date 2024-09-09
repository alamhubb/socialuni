package com.socialuni.social.common.api.model.user;

import com.socialuni.social.common.api.model.SocialuniContentIdRO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
public class SocialuniUserRO extends SocialuniContentIdRO {
    private String nickname;
    private String avatar;
    private String gender;
    private Integer age;

    public SocialuniUserRO(SocialuniUserRO userRO) {
        super(userRO);
        this.nickname = userRO.getNickname();
        this.avatar = userRO.getAvatar();
        this.gender = userRO.getGender();
        this.age = userRO.getAge();
    }
}
