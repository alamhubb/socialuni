package com.socialuni.social.sdk.dao.repository;


import com.socialuni.social.sdk.dao.DO.UniOutRegisterUserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniOutRegisterUserRepository extends JpaRepository<UniOutRegisterUserDO, Integer> {

    UniOutRegisterUserDO findByDevIdAndUserId(Integer devId, Integer userId);

}

