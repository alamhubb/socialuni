package com.socialuni.social.user.sdk.repository;

import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SocialuniUserInfoRepository extends JpaRepository<SocialuniUserInfoBaseDO, Integer> {
    SocialuniUserInfoBaseDO findByUserId(Integer userId);
}

