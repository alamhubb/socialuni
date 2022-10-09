package com.socialuni.social.sdk.logic.entity.user;

import com.socialuni.social.user.sdk.api.SocialUserViolationApi;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.model.SocialUserViolationModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialUserViolationEntity {
    @Resource
    private SocialUserViolationApi socialUserViolationApi;

    public SocialUserViolationModel getOrCreateViolationDO(Integer userId) {
        SocialUserViolationModel socialUserViolationModel = SocialuniUserUtil.getUserViolationDO(userId);
        if (socialUserViolationModel == null) {
            socialUserViolationModel = socialUserViolationApi.save(new SocialUserViolationModel(userId));
        }
        return socialUserViolationModel;
    }
}
