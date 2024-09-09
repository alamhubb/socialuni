package com.socialuni.social.likee.logic.domain;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeDO;
import com.socialuni.social.likee.logic.manage.SocialuniUserLikeManage;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

public class SocialuniLikeDomain {
    @Resource
    SocialuniUserLikeManage socialuniUserLikeManage;

    public void asfaf(SocialuniUserIdQO addVO) {
        //有问题，应该关注完刷新前台用户
        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Long beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getUserId());
        if (Objects.equals(beUserId, mineUserId)) {
            throw new SocialParamsException("不能喜欢自己哦");
        }
        SocialuniUserLikeDO socialuniUserLikeDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, beUserId, SocialuniUserLikeDO.class);

        if (socialuniUserLikeDO == null) {
            socialuniUserLikeDO = socialuniUserLikeManage.createUserLike(mineUserId, beUserId);
        } else {
            if (socialuniUserLikeDO.getStatus().equals(SocialuniCommonStatus.enable)) {
                throw new SocialBusinessException("已经喜欢过此用户了");
            }
            socialuniUserLikeDO.setStatus(SocialuniCommonStatus.enable);
        }


    }

    public SocialuniUserLikeDO updateLikeStatue(SocialuniUserLikeDO userLikeDO, String status) {
        userLikeDO.setStatus(status);
        userLikeDO.setUpdateTime(new Date());
        userLikeDO = SocialuniUserContactRepositoryFacede.save(userLikeDO);
        return userLikeDO;
    }
}
