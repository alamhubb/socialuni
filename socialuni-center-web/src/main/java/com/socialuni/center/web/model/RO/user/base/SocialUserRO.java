package com.socialuni.center.web.model.RO.user.base;

import com.socialuni.center.web.model.RO.community.SocialContentIdRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialUserRO extends SocialContentIdRO {
    private String nickname;
    private String avatar;
    private String gender;

    public SocialUserRO(SocialContentIdRO userRO) {
        super(userRO);
    }

    public SocialUserRO(SocialUserRO userRO) {
        super(userRO);
        this.nickname = userRO.getNickname();
        this.avatar = userRO.getAvatar();
        this.gender = userRO.getGender();
    }
}
