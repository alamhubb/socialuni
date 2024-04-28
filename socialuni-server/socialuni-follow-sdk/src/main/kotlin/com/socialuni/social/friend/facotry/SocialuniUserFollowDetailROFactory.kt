package com.socialuni.social.user.sdk.model.factory

import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO
import com.socialuni.social.common.api.model.user.SocialuniUserFollowInfoRO
import com.socialuni.social.common.sdk.utils.ListConvertUtil
import com.socialuni.social.user.sdk.logic.manage.SocialUserFansDetailManage
import com.socialuni.social.user.sdk.dao.DO.SocialUserFansDetailDo
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo
import com.socialuni.social.friend.dao.DO.SocialuniUserFollowDO
import com.socialuni.social.friend.facotry.SocialuniContentUserROFactory
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil
import org.springframework.stereotype.Component
import javax.annotation.Resource
import java.util.List

@Component
class SocialuniUserFollowDetailROFactory {
    @Resource
    fun setSocialUserFansDetailManage(socialUserFansDetailManage: SocialUserFansDetailManage?) {
        Companion.socialUserFansDetailManage = socialUserFansDetailManage
    }

    companion object {
        var socialUserFansDetailManage: SocialUserFansDetailManage? = null

        fun newSocialFollowUserRO(user: SocialuniUserDo?, mineUser: SocialuniUserDo?): SocialuniUserFollowInfoRO {
            val SocialUserFansDetailDo: SocialUserFansDetailDo =
                socialUserFansDetailManage.getOrCreateUserFollowDetail(user)

            return newSocialFollowUserRO(user, mineUser, SocialUserFansDetailDo)
        }

        fun newSocialFollowUserRO(
            user: SocialuniUserDo?,
            mineUser: SocialuniUserDo?,
            SocialUserFansDetailDo: SocialUserFansDetailDo
        ): SocialuniUserFollowInfoRO {
            val userRO: SocialuniUserFollowInfoRO =
                SocialuniUserFollowInfoRO(SocialuniContentUserROFactory.newContentUserRO(user, mineUser))
            //用户关注粉丝数
            userRO.setFansNum(SocialUserFansDetailDo.getFansNum())
            //        if (mineUser != null && userRO.getIsMine()) {
            //非自己也显示关注数量
            userRO.setFollowNum(SocialUserFansDetailDo.getFollowNum())
            //        }
            return userRO
        }

        fun getUsers(users: List<SocialuniUserDo?>?, mineUser: SocialuniUserDo?): List<SocialuniUserFollowInfoRO> {
            return ListConvertUtil.toList(SocialuniUserFollowDetailROFactory::newSocialFollowUserRO, users, mineUser)
        }

        fun getFollowUserLists(
            users: List<SocialuniUserFollowDO?>?,
            mineUser: SocialuniUserDo?
        ): List<SocialuniUserFollowDetailListRO> {
            return ListConvertUtil.toList({ followDO, user ->
                val userDo: SocialuniUserDo = SocialuniUserUtil.getAndCheckUserNotNull(followDO.getBeUserId())
                val socialuniUserFollowDetailRO: SocialuniUserFollowInfoRO = newSocialFollowUserRO(userDo, user)
                val socialuniUserFollowDetailListRO: SocialuniUserFollowDetailListRO =
                    SocialuniUserFollowDetailListRO(socialuniUserFollowDetailRO)
                socialuniUserFollowDetailListRO.setUpdateTime(followDO.getUpdateTime())
                socialuniUserFollowDetailListRO
            }, users, mineUser)
        }

        fun getFansUserLists(
            users: List<SocialuniUserFollowDO?>?,
            mineUser: SocialuniUserDo?
        ): List<SocialuniUserFollowDetailListRO> {
            return ListConvertUtil.toList({ followDO ->
                val userDo: SocialuniUserDo = SocialuniUserUtil.getAndCheckUserNotNull(followDO.getUserId())
                val socialuniUserFollowDetailRO: SocialuniUserFollowInfoRO = newSocialFollowUserRO(userDo, mineUser)
                val socialuniUserFollowDetailListRO: SocialuniUserFollowDetailListRO =
                    SocialuniUserFollowDetailListRO(socialuniUserFollowDetailRO)
                socialuniUserFollowDetailListRO.setUpdateTime(followDO.getUpdateTime())
                socialuniUserFollowDetailListRO
            }, users)
        }
    }
}
