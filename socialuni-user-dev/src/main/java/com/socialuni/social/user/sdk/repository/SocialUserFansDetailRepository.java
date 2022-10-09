package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.api.SocialUserFansDetailApi;
import com.socialuni.social.user.sdk.entity.SocialUserFansDetailDo;
import com.socialuni.social.user.sdk.model.SocialUserFansDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserFansDetailRepository extends SocialUserFansDetailApi, JpaRepository<SocialUserFansDetailDo, Integer> {
    SocialUserFansDetailModel findByUserId(Integer userId);

    SocialUserFansDetailModel save(SocialUserFansDetailModel socialUserFansDetailModel);
}

