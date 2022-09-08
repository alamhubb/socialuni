package com.socialuni.sdk.dao.repository.user;


import com.socialuni.sdk.dao.DO.user.SocialUserFansDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserFansDetailRepository extends JpaRepository<SocialUserFansDetailDO, Integer> {
    SocialUserFansDetailDO findByUserId(Integer userId);

    SocialUserFansDetailDO save(SocialUserFansDetailDO socialUserFansDetailDO);
}

