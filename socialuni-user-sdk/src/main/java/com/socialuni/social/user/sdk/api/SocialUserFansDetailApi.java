package com.socialuni.social.user.sdk.api;


import com.socialuni.social.user.sdk.model.SocialUserFansDetailModel;

public interface SocialUserFansDetailApi {
    SocialUserFansDetailModel findByUserId(Integer userId);

    SocialUserFansDetailModel save(SocialUserFansDetailModel socialUserFansDetailModel);
}

