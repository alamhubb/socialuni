package com.socialuni.center.web.repository;


import com.socialuni.center.web.model.DO.chat.UserContactDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface UserContactRepository extends JpaRepository<UserContactDO, Integer> {
    Optional<UserContactDO> findFirstByUserIdAndBeUserIdAndStatusAndType(Integer userId, Integer beUserId, String status, String type);


    UserContactDO findOneByUserIdAndBeUserIdAndStatusAndType(Integer userId, Integer beUserId, String status, String type);
}

