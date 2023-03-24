package com.socialuni.social.common.sdk.dao.repository;


import com.socialuni.social.common.sdk.dao.DO.PushMessageDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface PushMessageRepository extends JpaRepository<PushMessageDO, Integer> {
}