package com.socialuni.sdk.dao.repository;

import com.socialuni.sdk.dao.DO.notify.PushMessageDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface PushMessageRepository extends JpaRepository<PushMessageDO, Integer> {
}