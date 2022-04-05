package com.socialuni.social.sdk.repository.user.identity;


import com.socialuni.social.entity.model.DO.user.SocialUserIdentityAuthDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserIdentityAuthRepository extends JpaRepository<SocialUserIdentityAuthDO, Integer> {

    SocialUserIdentityAuthDO findFirstByUserId(Integer userId);

}

