package com.socialuni.sdk.factory.user;

import com.socialuni.sdk.factory.user.base.SocialUserROFactory;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

@Component
public class SocialCommentUserROFactory {
    public static SocialCommentUserRO newCommentUserRO(Integer commentUserId) {
        UserDO userDO = SocialUserUtil.get(commentUserId);
        return new SocialCommentUserRO(SocialUserROFactory.getUserRO(userDO));
    }

    public static SocialCommentUserRO newCommentUserRO(UserDO userDO) {
        return new SocialCommentUserRO(SocialUserROFactory.getUserRO(userDO));
    }
}
