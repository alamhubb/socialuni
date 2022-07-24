package com.socialuni.api.model.RO.user;

import com.socialuni.api.model.RO.SocialuniUidRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class CenterUserRO extends SocialuniUidRO {
    //必须为string，返回给app后是uuid无法变为int
    private String id;
    private String nickname;
    private String avatar;
    private String gender;

    public CenterUserRO(CenterUserRO centerUserRO) {
        super(centerUserRO);
        this.nickname = centerUserRO.getNickname();
        this.avatar = centerUserRO.getAvatar();
        this.gender = centerUserRO.getGender();
    }

    public CenterUserRO(SocialUserRO userRO) {
        this.nickname = userRO.getNickname();
        this.avatar = userRO.getAvatar();
        this.gender = userRO.getGender();
    }
}
