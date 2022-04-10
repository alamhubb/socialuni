package com.socialuni.social.sdk.repository.user.identity;


import com.socialuni.social.entity.model.DO.user.SocialUserIdentityAuthDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialUserIdentityAuthRepository extends JpaRepository<SocialUserIdentityAuthDO, Integer> {

    SocialUserIdentityAuthDO findFirstByUserId(Integer userId);

    List<SocialUserIdentityAuthDO> findByStatusIn(List<String> status);
}

