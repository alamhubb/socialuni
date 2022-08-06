package com.socialuni.center.web.repository;


import com.socialuni.center.web.model.DO.UniOutRegisterUserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniOutRegisterUserRepository extends JpaRepository<UniOutRegisterUserDO, Integer> {

    UniOutRegisterUserDO findByDevIdAndUserId(Integer devId, Integer userId);

}

