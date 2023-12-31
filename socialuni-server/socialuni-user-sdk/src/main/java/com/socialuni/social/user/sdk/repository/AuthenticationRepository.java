package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.dao.DO.AuthenticationDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface AuthenticationRepository extends JpaRepository<AuthenticationDO, Integer> {
    AuthenticationDO findFirstByPhoneNumOrderByCreateTimeDescIdAsc(String phoneNum);

    Integer countByPhoneNum(String phoneNum);

    Integer countByIp(String ip);

    Integer countByUserId(Integer userId);
}


