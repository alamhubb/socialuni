package com.socialuni.social.user.sdk.api.repository;


import com.socialuni.social.common.sdk.model.SocialuniUserInfoBaseModel;

public interface SocialuniUserInfoRepositoryInterface {

    SocialuniUserInfoBaseModel findByUserId(Integer userId);

}

