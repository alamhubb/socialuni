package com.socialuni.api.model;

import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class CenterUserFollowDetailRO extends CenterContentUserRO {
    private Integer fansNum;
    // 保护用户隐私，仅为自己才可查看
    private Integer followNum;

    public CenterUserFollowDetailRO(CenterUserFollowDetailRO userRO) {
        super(userRO);
        this.fansNum = userRO.getFansNum();
        this.followNum = userRO.getFollowNum();
    }

    public CenterUserFollowDetailRO(CenterContentUserRO centerContentUserRO, SocialUserFollowDetailRO userRO) {
        super(centerContentUserRO);
        this.fansNum = userRO.getFansNum();
        this.followNum = userRO.getFollowNum();
    }
}
