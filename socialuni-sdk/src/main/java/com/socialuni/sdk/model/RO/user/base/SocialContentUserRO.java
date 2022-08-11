package com.socialuni.sdk.model.RO.user.base;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialContentUserRO extends SocialListUserRO {
    private Boolean hasFollowed;
    //是否被对方关注
    private Boolean hasBeFollowed;
    private Boolean isMine;
    private Boolean identityAuth;

    public SocialContentUserRO(SocialListUserRO userRO) {
        super(userRO);
    }

    public SocialContentUserRO(SocialContentUserRO userRO) {
        super(userRO);
        this.hasFollowed = userRO.getHasFollowed();
        this.hasBeFollowed = userRO.getHasBeFollowed();
        this.isMine = userRO.getIsMine();
        this.identityAuth = userRO.getIdentityAuth();
    }
}
