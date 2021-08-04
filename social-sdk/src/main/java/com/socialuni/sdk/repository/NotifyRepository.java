package com.socialuni.sdk.repository;

import com.socialuni.sdk.model.DO.NotifyDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface NotifyRepository extends JpaRepository<NotifyDO, Integer> {
    List<NotifyDO> findTop20ByReceiveUserIdAndTypeInOrderByHasReadDescIdDesc(Integer receiveUserId, List<String> types);

    List<NotifyDO> findAllByReceiveUserIdAndTypeInAndHasReadFalseOrderByIdDesc(Integer receiveUserId, List<String> types);
}