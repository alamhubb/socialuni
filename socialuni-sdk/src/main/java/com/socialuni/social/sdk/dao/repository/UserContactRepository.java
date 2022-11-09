package com.socialuni.social.sdk.dao.repository;


import com.socialuni.social.sdk.dao.DO.chat.SocialuniGetUserContactRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface UserContactRepository extends JpaRepository<SocialuniGetUserContactRecordDO, Integer> {
    Optional<SocialuniGetUserContactRecordDO> findFirstByUserIdAndBeUserId(Integer userId, Integer beUserId);


    SocialuniGetUserContactRecordDO findOneByUserIdAndBeUserId(Integer userId, Integer beUserId);
}


