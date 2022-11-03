package com.socialuni.social.user.sdk.repository;

import com.socialuni.social.common.sdk.entity.SocialuniUserInfoBaseDO;
import com.socialuni.social.user.sdk.api.repository.SocialuniUserInfoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SocialuniUserInfoRepository extends JpaRepository<SocialuniUserInfoBaseDO, Integer> {
    SocialuniUserInfoBaseDO findByUserId(Integer userId);
}

