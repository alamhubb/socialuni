package com.socialuni.social.sdk.factory.user.base;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.base.SocialContentUserRO;
import com.socialuni.social.sdk.manage.FollowManage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialContentUserROFactory {
    static FollowManage followManage;

    @Resource
    public void setFollowManage(FollowManage followManage) {
        SocialContentUserROFactory.followManage = followManage;
    }

    public static SocialContentUserRO newContentUserRO(UserDO user, UserDO mineUser) {
        SocialContentUserRO userRO = new SocialContentUserRO(SocialListUserROFactory.getListUserRO(user));

        userRO.setHasFollowed(false);
        userRO.setHasBeFollowed(false);

        if (mineUser != null && user.getId().equals(mineUser.getId())) {
            userRO.setIsMine(true);
        } else {
            userRO.setIsMine(false);
        }

        if (mineUser != null && !userRO.getIsMine()) {
            boolean hasFollowUser = followManage.userHasFollowBeUser(mineUser.getId(), user.getId());
            userRO.setHasFollowed(hasFollowUser);

            boolean hasBeFollowed = followManage.userHasFollowBeUser(user.getId(), mineUser.getId());
            userRO.setHasBeFollowed(hasBeFollowed);
        }
        return userRO;
    }

}
