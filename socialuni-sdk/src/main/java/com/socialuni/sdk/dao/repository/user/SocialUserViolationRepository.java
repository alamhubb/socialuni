package com.socialuni.sdk.dao.repository.user;


import com.socialuni.sdk.model.DO.user.SocialUserViolationDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserViolationRepository extends JpaRepository<SocialUserViolationDO, Integer> {
    SocialUserViolationDO findOneByUserId(Integer userId);
}

