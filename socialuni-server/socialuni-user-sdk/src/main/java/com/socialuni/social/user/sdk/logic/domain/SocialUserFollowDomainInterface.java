package com.socialuni.social.user.sdk.logic.domain;

import com.socialuni.social.user.sdk.model.DO.SocialuniUserFollowDO;
import com.socialuni.social.user.sdk.model.QO.follow.SocialuniUserFollowAddQO;

public interface SocialUserFollowDomainInterface {
    SocialuniUserFollowDO addFlow(SocialuniUserFollowAddQO addVO);

    void cancelFollow(SocialuniUserFollowAddQO addVO);
}
