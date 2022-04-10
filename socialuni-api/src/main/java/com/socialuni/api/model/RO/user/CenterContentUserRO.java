package com.socialuni.api.model.RO.user;

import com.socialuni.social.model.model.RO.user.base.SocialContentUserRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class CenterContentUserRO extends CenterListUserRO {
    private Boolean hasFollowed;
    //是否被对方关注
    private Boolean hasBeFollowed;
    private Boolean isMine;
    private Boolean identityAuth;

    public CenterContentUserRO(CenterContentUserRO centerContentUserRO) {
        super(centerContentUserRO);
        this.hasFollowed = centerContentUserRO.getHasFollowed();
        this.hasBeFollowed = centerContentUserRO.getHasBeFollowed();
        this.isMine = centerContentUserRO.getIsMine();
        this.identityAuth = centerContentUserRO.getIdentityAuth();
    }

    public CenterContentUserRO(CenterListUserRO centerListUserRO, SocialContentUserRO userRO) {
        super(centerListUserRO);
        this.hasFollowed = userRO.getHasFollowed();
        this.hasBeFollowed = userRO.getHasBeFollowed();
        this.isMine = userRO.getIsMine();
        this.identityAuth = userRO.getIdentityAuth();
    }

    public CenterContentUserRO(SocialContentUserRO userRO) {
        super(userRO);
        this.hasFollowed = userRO.getHasFollowed();
        this.hasBeFollowed = userRO.getHasBeFollowed();
        this.isMine = userRO.getIsMine();
        this.identityAuth = userRO.getIdentityAuth();
    }
}
