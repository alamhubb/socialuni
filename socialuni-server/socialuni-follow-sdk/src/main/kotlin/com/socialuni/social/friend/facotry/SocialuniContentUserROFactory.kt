package com.socialuni.social.friend.facotry

import com.socialuni.social.common.api.model.user.SocialuniContentUserRO
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo
import com.socialuni.social.user.sdk.constant.UserIdentityAuthStatus
import com.socialuni.social.user.sdk.logic.manage.SocialuniUserFollowManage
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory
import com.socialuni.social.user.sdk.repository.SocialUserIdentityAuthRepository
import org.springframework.stereotype.Component
import java.util.stream.Collectors
import javax.annotation.Resource

@Component
class SocialuniContentUserROFactory {
    @Resource
    fun setSocialUserIdentityAuthRepository(socialUserIdentityAuthRepository: SocialUserIdentityAuthRepository?) {
        Companion.socialUserIdentityAuthRepository = socialUserIdentityAuthRepository
    }

    @Resource
    fun setFollowManage(followManage: SocialuniUserFollowManage?) {
        Companion.followManage = followManage
    }

    companion object {
        var followManage: SocialuniUserFollowManage? = null

        var socialUserIdentityAuthRepository: SocialUserIdentityAuthRepository? = null

        fun newContentUserRO(user: SocialuniUserDo, mineUser: SocialuniUserDo?): SocialuniContentUserRO {
            val userRO = SocialuniContentUserRO(SocialuniUserROFactory.getUserRO(user, mineUser))

            userRO.hasFollowed = false
            userRO.hasBeFollowed = false
            userRO.identityAuth = false

            if (mineUser != null && user.unionId == mineUser.unionId) {
                userRO.isMine = true
            } else {
                userRO.isMine = false
            }

            if (mineUser != null && !userRO.isMine) {
                val hasFollowUser = followManage!!.userHasFollowBeUser(mineUser.unionId, user.unionId)
                userRO.hasFollowed = hasFollowUser

                val hasBeFollowed = followManage!!.userHasFollowBeUser(user.unionId, mineUser.unionId)
                userRO.hasBeFollowed = hasBeFollowed
            }

            val socialUserIdentityAuthDO = socialUserIdentityAuthRepository!!.findFirstByUserId(user.unionId)
            if (socialUserIdentityAuthDO != null) {
                if (UserIdentityAuthStatus.authSuccessList.contains(socialUserIdentityAuthDO.status)) {
                    userRO.identityAuth = true
                }
            }
            return userRO
        }

        fun toList(socialuniUserDos: List<SocialuniUserDo>, mineUser: SocialuniUserDo?): List<SocialuniContentUserRO> {
            return socialuniUserDos.stream().map { item: SocialuniUserDo -> newContentUserRO(item, mineUser) }
                .collect(Collectors.toList())
        }
    }
}
