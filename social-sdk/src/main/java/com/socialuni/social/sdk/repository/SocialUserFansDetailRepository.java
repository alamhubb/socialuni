package com.socialuni.social.sdk.repository;


import com.socialuni.social.entity.model.DO.user.SocialUserFansDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserFansDetailRepository extends JpaRepository<SocialUserFansDetailDO, Integer> {
    SocialUserFansDetailDO findByUserId(Integer userId);

    SocialUserFansDetailDO save(SocialUserFansDetailDO socialUserFansDetailDO);
}

