package com.socialuni.social.user.sdk.api;


import com.socialuni.social.user.sdk.model.SocialUserViolationModel;

public interface SocialUserViolationInterface {
    SocialUserViolationModel findOneByUserId(Integer userId);

    SocialUserViolationModel savePut(SocialUserViolationModel socialUserViolationModel);
}

