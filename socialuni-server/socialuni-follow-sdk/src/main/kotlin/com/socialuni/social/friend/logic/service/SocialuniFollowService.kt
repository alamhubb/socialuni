package com.socialuni.social.friend.logic.service

import com.socialuni.social.common.api.constant.SocialuniSystemConst
import com.socialuni.social.common.api.model.ResultRO
import com.socialuni.social.common.api.model.SocialuniPageQueryQO
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO
import com.socialuni.social.friend.api.SocialuniFollowAPI
import com.socialuni.social.friend.model.qo.SocialuniUserFollowAddQO
import com.socialuni.social.user.sdk.logic.domain.SocialQueryUserFollowsDomain
import com.socialuni.social.user.sdk.logic.domain.SocialUserFollowDomainInterface
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class SocialuniFollowService {
    @Resource
    private val socialUserFollowDomainInterface: SocialUserFollowDomainInterface? = null

    @Resource
    private val socialQueryUserFollowsDomain: SocialQueryUserFollowsDomain? = null

    @Resource
    var socialuniFollowAPI: SocialuniFollowAPI? = null

    fun addFollow(addQO: SocialuniUserFollowAddQO?): ResultRO<Void?>? {
        //有问题，应该关注完刷新前台用户
        socialUserFollowDomainInterface!!.addFlow(addQO)
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniFollowAPI!!.addFollow(addQO)
        }
        return ResultRO()
    }

    fun cancelFollow(addQO: SocialuniUserFollowAddQO?): ResultRO<Void?>? {
        //有问题，应该关注完刷新前台用户
        socialUserFollowDomainInterface!!.cancelFollow(addQO)
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniFollowAPI!!.cancelFollow(addQO)
        }
        return ResultRO()
    }

    fun queryUserFollows(socialuniPageQueryQO: SocialuniPageQueryQO<String?>?): ResultRO<List<SocialuniUserFollowDetailListRO?>?>? {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniFollowAPI!!.queryUserFollows(socialuniPageQueryQO)
        }
        val map = socialQueryUserFollowsDomain!!.queryUserFollows(socialuniPageQueryQO)
        return ResultRO(map)
    }
}
