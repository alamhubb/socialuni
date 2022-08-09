package com.socialuni.center.web.model.RO.user;

import com.socialuni.center.web.model.RO.community.UniContentIdRO;
import com.socialuni.center.web.model.RO.user.base.SocialUserRO;
import com.socialuni.social.constant.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class CenterUserRO extends UniContentIdRO {
    //必须为string，返回给app后是uuid无法变为int
    private String nickname;
    private String avatar;
    private String gender;

    public CenterUserRO(CenterUserRO centerUserRO) {
        super(ContentType.user, centerUserRO);
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
