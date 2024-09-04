package com.socialuni.social.likee.logic.manage;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeDO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SocialuniUserLikeManage {
    public SocialuniUserLikeDO createUserLike(Long mineUserId, Long beUserId) {
        SocialuniUserLikeDO userLikeDO = new SocialuniUserLikeDO();
        //添加喜欢
        userLikeDO.setUserId(mineUserId);
        userLikeDO.setBeUserId(beUserId);
        userLikeDO = SocialuniUserContactRepositoryFacede.save(userLikeDO);
        return userLikeDO;
    }

    public SocialuniUserLikeDO hasLikeUserRecord(Long mineUserId, Long beUserId) {
        SocialuniUserLikeDO socialuniUserLikeDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, beUserId, SocialuniUserLikeDO.class);

        return socialuniUserLikeDO;
    }

    public Boolean hasUserLike(Long mineUserId, Long beUserId) {
        SocialuniUserLikeDO socialuniUserLikeDO = hasLikeUserRecord(mineUserId, beUserId);
        if (socialuniUserLikeDO == null) {
            return false;
        }
        return socialuniUserLikeDO.getStatus().equals(SocialuniCommonStatus.enable);
    }


    public SocialuniUserLikeDO createOrUpdateLikeStatus(Long mineUserId, Long beUserId) {
        SocialuniUserLikeDO socialuniUserLikeDO = hasLikeUserRecord(mineUserId, beUserId);

        if (socialuniUserLikeDO == null) {
            socialuniUserLikeDO = createUserLike(mineUserId, beUserId);
        } else {
            if (socialuniUserLikeDO.getStatus().equals(SocialuniCommonStatus.enable)) {
                throw new SocialBusinessException("已经喜欢过此用户了");
            }
            updateLikeStatus(socialuniUserLikeDO, SocialuniCommonStatus.enable);
        }
        return socialuniUserLikeDO;
    }


    public SocialuniUserLikeDO updateLikeStatus(SocialuniUserLikeDO userLikeDO, String status) {
        userLikeDO.setStatus(status);
        userLikeDO.setUpdateTime(new Date());
        userLikeDO = SocialuniUserContactRepositoryFacede.save(userLikeDO);
        return userLikeDO;
    }
}
