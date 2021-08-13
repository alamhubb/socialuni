package com.socialuni.admin.web.controller;


import com.socialuni.entity.model.DevAuthCodeDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevAuthCodeRepository extends JpaRepository<DevAuthCodeDO, Integer> {
    Optional<DevAuthCodeDO> findFirstByPhoneNumOrderByCreateTimeDescIdAsc(String phoneNum);

    Integer countByPhoneNum(String phoneNum);

    Integer countByIp(String ip);

    Integer countByUserId(Integer userId);
}
