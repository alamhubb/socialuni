package com.socialuni.social.user.sdk.dao.repository;

import com.qingchi.qing.jpa.SocialuniUserInfoBaseDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SocialuniUserInfoRepository extends JpaRepository<SocialuniUserInfoBaseDO, Integer> {
    SocialuniUserInfoBaseDO findByUserId(Long userId);
}

