package com.socialuni.social.common.sdk.dao.repository;


import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface SocialuniUserContactFacedeRepository<T extends SocialuniUserContactBaseDO> extends JpaRepository<T, Integer> {

   Long countByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status);
}

