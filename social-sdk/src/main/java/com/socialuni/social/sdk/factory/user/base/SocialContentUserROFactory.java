package com.socialuni.social.sdk.factory.user.base;

import com.socialuni.social.model.model.RO.user.base.SocialContentUserRO;
import com.socialuni.social.sdk.dao.FollowDao;
import com.socialuni.social.entity.model.DO.user.UserDO;

import javax.annotation.Resource;

public class SocialContentUserROFactory {
    static FollowDao followDao;

    @Resource
    public void setFollowDao(FollowDao followDao) {
        SocialContentUserROFactory.followDao = followDao;
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
            boolean hasFollowUser = followDao.userHasFollowBeUser(mineUser.getId(), user.getId());
            userRO.setHasFollowed(hasFollowUser);

            boolean hasBeFollowed = followDao.userHasFollowBeUser(user.getId(), mineUser.getId());
            userRO.setHasBeFollowed(hasBeFollowed);
        }
        return userRO;
    }

}
