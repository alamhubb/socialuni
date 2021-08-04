package com.socialuni.sdk.factory.user;

import com.socialuni.social.model.model.RO.user.SocialTalkUserRO;
import com.socialuni.sdk.factory.user.base.SocialContentUserROFactory;
import com.socialuni.sdk.model.DO.user.UserDO;

public class SocialTalkUserROFactory {

    public static SocialTalkUserRO newTalkUserRO(UserDO user, UserDO mineUser) {
        SocialTalkUserRO socialTalkUserRO = new SocialTalkUserRO(SocialContentUserROFactory.newContentUserRO(user, mineUser));
//        socialTalkUserRO.setHasFollowed(false);
        return socialTalkUserRO;
    }

}
