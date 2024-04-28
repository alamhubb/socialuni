package com.socialuni.social.user.sdk.logic.domain

import com.socialuni.social.friend.dao.DO.SocialuniUserFollowDO
import com.socialuni.social.friend.model.qo.SocialuniUserFollowAddQO

interface SocialUserFollowDomainInterface {
    fun addFlow(addVO: SocialuniUserFollowAddQO?): SocialuniUserFollowDO?

    fun cancelFollow(addVO: SocialuniUserFollowAddQO?)
}
