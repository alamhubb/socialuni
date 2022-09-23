package com.socialuni.social.sdk.dao.repository.user;


import com.socialuni.social.sdk.dao.DO.user.SocialUserViolationDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserViolationRepository extends JpaRepository<SocialUserViolationDO, Integer> {
    SocialUserViolationDO findOneByUserId(Integer userId);
}

