package com.socialuni.center.web.repository.user;


import com.socialuni.center.web.model.DO.user.SocialUserFansDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserFansDetailRepository extends JpaRepository<SocialUserFansDetailDO, Integer> {
    SocialUserFansDetailDO findByUserId(Integer userId);

    SocialUserFansDetailDO save(SocialUserFansDetailDO socialUserFansDetailDO);
}

