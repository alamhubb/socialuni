package com.socialuni.sdk.dao.repository;

import com.socialuni.sdk.dao.DO.user.SocialuniUserExpandDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserExpandRepository extends JpaRepository<SocialuniUserExpandDO, Integer> {

    SocialuniUserExpandDO findByUserId(Integer userId);

}

