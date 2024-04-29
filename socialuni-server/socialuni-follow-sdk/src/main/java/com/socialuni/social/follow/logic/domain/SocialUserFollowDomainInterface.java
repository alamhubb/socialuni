package com.socialuni.social.follow.logic.domain;

import com.socialuni.social.follow.dao.DO.SocialuniUserFollowDO;
import com.socialuni.social.follow.model.follow.SocialuniUserFollowAddQO;

public interface SocialUserFollowDomainInterface {
    SocialuniUserFollowDO addFlow(SocialuniUserFollowAddQO addVO);

    void cancelFollow(SocialuniUserFollowAddQO addVO);
}
