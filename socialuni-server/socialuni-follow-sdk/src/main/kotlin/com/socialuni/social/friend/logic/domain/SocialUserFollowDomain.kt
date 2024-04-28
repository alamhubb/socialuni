package com.socialuni.social.friend.logic.domain

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus
import com.socialuni.social.common.api.exception.exception.SocialParamsException
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede
import com.socialuni.social.friend.dao.DO.SocialuniUserFollowDO
import com.socialuni.social.friend.dao.redis.SocialuniUserFollowRedis
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserBlackDO
import com.socialuni.social.user.sdk.logic.domain.SocialUserFollowDomainInterface
import com.socialuni.social.user.sdk.logic.manage.SocialUserFansDetailManage
import com.socialuni.social.user.sdk.logic.manage.SocialuniUserFollowManage
import com.socialuni.social.friend.model.qo.SocialuniUserFollowAddQO
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil
import org.springframework.stereotype.Service
import javax.annotation.Resource
import javax.transaction.Transactional

@Service
class SocialUserFollowDomain : SocialUserFollowDomainInterface {
    @Resource
    private val followRedis: SocialuniUserFollowRedis? = null

    @Resource
    private val socialUserFansDetailManage: SocialUserFansDetailManage? = null

    @Resource
    var followManage: SocialuniUserFollowManage? = null

    @Transactional
    override fun addFlow(addVO: SocialuniUserFollowAddQO): SocialuniUserFollowDO {
        //有问题，应该关注完刷新前台用户
        val mineUserId = SocialuniUserUtil.getMineUserIdNotNull()

        val beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.beUserId)
        if (beUserId == mineUserId) {
            throw SocialParamsException("不能自己关注自己哦")
        }
        val hasFollow = followManage!!.userHasFollowBeUser(mineUserId, beUserId)
        if (hasFollow) {
            throw SocialParamsException("已经关注过此用户了")
        }
        socialUserFansDetailManage!!.mineFollowNumAdd(mineUserId)
        socialUserFansDetailManage.beUserFansNumAdd(beUserId)
        var followDO = followRedis!!.findFirstByUserIdAndBeUserId(mineUserId, beUserId)
        //未关注过
        followDO = if (followDO == null) {
            followManage!!.createFollow(mineUserId, beUserId)
        } else {
            //已经关注
            followManage!!.updateFollow(followDO, SocialuniCommonStatus.enable)
        }

        val socialuniUserBlackDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndStatus(
            mineUserId, beUserId, SocialuniCommonStatus.enable,
            SocialuniUserBlackDO::class.java
        )
        //如果您把对方拉黑了，重新关注后则取消拉黑
        if (socialuniUserBlackDO != null) {
            socialuniUserBlackDO.status = SocialuniCommonStatus.delete
            SocialuniUserContactRepositoryFacede.save(socialuniUserBlackDO)
        }
        return followDO
    }

    override fun cancelFollow(addVO: SocialuniUserFollowAddQO) {
        //有问题，应该关注完刷新前台用户
        val mineUserId = SocialuniUserUtil.getMineUserIdNotNull()
        val beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.beUserId)
        if (beUserId == mineUserId) {
            throw SocialParamsException("不能自己取消关注自己哦")
        }
        val hasFollow = followManage!!.userHasFollowBeUser(mineUserId, beUserId)
        if (!hasFollow) {
            throw SocialParamsException("并没有关注此用户")
        }
        socialUserFansDetailManage!!.mineFollowNumSub(mineUserId)
        socialUserFansDetailManage.beUserFansNumSub(beUserId)
        val followDO = followRedis!!.findFirstByUserIdAndBeUserId(mineUserId, beUserId)
        followManage!!.updateFollow(followDO, SocialuniCommonStatus.delete)
    }
}