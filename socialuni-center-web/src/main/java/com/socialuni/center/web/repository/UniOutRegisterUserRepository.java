package com.socialuni.center.web.repository;


import com.socialuni.social.entity.model.DO.UniOutRegisterUserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniOutRegisterUserRepository extends JpaRepository<UniOutRegisterUserDO, Integer> {

    UniOutRegisterUserDO findByDevIdAndUserUnionId(Integer devId, Integer userUnionId);

}

