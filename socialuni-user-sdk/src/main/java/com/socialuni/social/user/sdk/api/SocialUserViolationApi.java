package com.socialuni.social.user.sdk.api;


import com.socialuni.social.user.sdk.model.SocialUserViolationModel;

public interface SocialUserViolationApi {
    SocialUserViolationModel findOneByUserId(Integer userId);

    SocialUserViolationModel savePut(SocialUserViolationModel socialUserViolationModel);
}

