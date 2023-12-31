package com.socialuni.social.common.api.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserFollowInfoRO extends SocialuniContentUserRO {
    private Integer fansNum;
    // 保护用户隐私，仅为自己才可查看
    private Integer followNum;

    public SocialuniUserFollowInfoRO(SocialuniUserFollowInfoRO userRO) {
        super(userRO);
        this.fansNum = userRO.getFansNum();
        this.followNum = userRO.getFollowNum();
    }

    public SocialuniUserFollowInfoRO(SocialuniContentUserRO centerContentUserRO) {
        super(centerContentUserRO);
    }
}
