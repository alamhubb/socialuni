package com.socialuni.social.model.model.RO.user;

import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialUserDetailRO extends SocialUserFollowDetailRO {
    private List<UserImgRO> imgs;

    public SocialUserDetailRO(SocialUserFollowDetailRO userRO) {
        super(userRO);
    }

    public SocialUserDetailRO(SocialUserDetailRO userRO) {
        super(userRO);

        this.imgs = userRO.getImgs();
    }
}
