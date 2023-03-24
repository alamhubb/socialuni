package com.socialuni.social.sdk.dao.repository;


import com.socialuni.social.common.sdk.dao.DO.keywords.NotifyDO;
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