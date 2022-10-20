package com.socialuni.social.user.sdk.api;


import com.socialuni.social.user.sdk.model.SocialUserFansDetailModel;

public interface SocialUserFansDetailInterface {
    SocialUserFansDetailModel findByUserId(Integer userId);

    SocialUserFansDetailModel savePut(SocialUserFansDetailModel socialUserFansDetailModel);
}

