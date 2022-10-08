package com.socialuni.social.user.sdk.api;


import com.socialuni.social.user.sdk.model.SocialUserFansDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserFansDetailRepository extends JpaRepository<SocialUserFansDetailDO, Integer> {
    SocialUserFansDetailDO findByUserId(Integer userId);

    SocialUserFansDetailDO save(SocialUserFansDetailDO socialUserFansDetailDO);
}

