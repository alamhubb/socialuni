package com.socialuni.admin.web.controller;


import com.socialuni.entity.model.DevAuthenticationDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevAuthenticationRepository extends JpaRepository<DevAuthenticationDO, Integer> {
    Optional<DevAuthenticationDO> findFirstByPhoneNumOrderByCreateTimeDescIdAsc(String phoneNum);

    Integer countByPhoneNum(String phoneNum);

    Integer countByIp(String ip);

    Integer countByUserId(Integer userId);
}
