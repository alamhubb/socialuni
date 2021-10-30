package com.socialuni.social.sdk.repository.user;


import com.socialuni.social.entity.model.DO.user.SocialUserViolationDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserViolationRepository extends JpaRepository<SocialUserViolationDO, Integer> {
    SocialUserViolationDO findOneByUserId(Integer userId);
}

