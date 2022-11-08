package com.socialuni.admin.web.repository;


import com.socialuni.social.sdk.dao.DO.dev.DevAuthCodeDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevAuthCodeRepository extends JpaRepository<DevAuthCodeDO, Integer> {
    DevAuthCodeDO findFirstByPhoneNumOrderByCreateTimeDescIdAsc(String phoneNum);

    Integer countByPhoneNum(String phoneNum);

    Integer countByIp(String ip);

//    Integer countByUserId(Integer userId);
}
