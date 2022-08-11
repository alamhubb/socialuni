package com.socialuni.sdk.repository;


import com.socialuni.sdk.model.DO.UniOutRegisterUserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniOutRegisterUserRepository extends JpaRepository<UniOutRegisterUserDO, Integer> {

    UniOutRegisterUserDO findByDevIdAndUserId(Integer devId, Integer userId);

}

