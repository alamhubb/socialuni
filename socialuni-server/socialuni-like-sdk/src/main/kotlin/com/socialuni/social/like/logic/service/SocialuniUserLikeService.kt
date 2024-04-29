package com.socialuni.social.like.logic.service

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus
import com.socialuni.social.common.api.exception.exception.SocialBusinessException
import com.socialuni.social.common.api.exception.exception.SocialParamsException
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede
import com.socialuni.social.like.dao.DO.SocialuniUserLikeDO
import com.socialuni.social.like.logic.manage.SocialuniUserLikeManage
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil
import org.springframework.stereotype.Service
import javax.annotation.Resource
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Service
class SocialuniUserLikeService {
    @Resource
    var socialuniUserLikeManage: SocialuniUserLikeManage? = null

    fun addLike(@NotNull addVO: @Valid SocialuniUserIdQO?): SocialuniUserLikeDO {
        //有问题，应该关注完刷新前台用户
        val mineUserId = SocialuniUserUtil.getMineUserIdNotNull()

        val beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO!!.userId)
        if (beUserId == mineUserId) {
            throw SocialParamsException("不能喜欢自己哦")
        }
        var socialuniUserLikeDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(
            mineUserId,
            beUserId,
            SocialuniUserLikeDO::class.java
        )
        if (socialuniUserLikeDO == null) {
            socialuniUserLikeDO = socialuniUserLikeManage!!.createUserLike(mineUserId, beUserId)
        } else {
            if (socialuniUserLikeDO.status == SocialuniCommonStatus.enable) {
                throw SocialBusinessException("已经喜欢过此用户了")
            }
            socialuniUserLikeDO.status = SocialuniCommonStatus.enable
        }
        return socialuniUserLikeDO
    }
}