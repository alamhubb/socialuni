package com.socialuni.social.common.api.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniContentUserRO extends SocialuniUserRO {
    private Boolean hasFollowed;
    //是否被对方关注
    private Boolean hasBeFollowed;
    private Boolean identityAuth;

    public SocialuniContentUserRO(SocialuniContentUserRO centerContentUserRO) {
        super(centerContentUserRO);
        this.hasFollowed = centerContentUserRO.getHasFollowed();
        this.hasBeFollowed = centerContentUserRO.getHasBeFollowed();
        this.identityAuth = centerContentUserRO.getIdentityAuth();
    }

    public SocialuniContentUserRO(SocialuniUserRO socialuniUserRO) {
        super(socialuniUserRO);
    }
}
