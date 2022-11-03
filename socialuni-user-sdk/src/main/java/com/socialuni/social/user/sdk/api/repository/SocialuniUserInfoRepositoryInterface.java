package com.socialuni.social.user.sdk.api.repository;


import com.socialuni.social.common.sdk.entity.SocialuniUserInfoBaseDO;

public interface SocialuniUserInfoRepositoryInterface {

    SocialuniUserInfoBaseDO findByUserId(Integer userId);

}

