package com.socialuni.social.user.sdk.api;


import com.socialuni.social.user.sdk.model.SocialUserViolationDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserViolationRepository extends JpaRepository<SocialUserViolationDO, Integer> {
    SocialUserViolationDO findOneByUserId(Integer userId);
}

