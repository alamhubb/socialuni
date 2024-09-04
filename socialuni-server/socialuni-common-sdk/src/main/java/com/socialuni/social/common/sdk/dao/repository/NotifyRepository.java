package com.socialuni.social.common.sdk.dao.repository;


import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface NotifyRepository extends JpaRepository<NotifyDO, Integer> {
    List<NotifyDO> findTop20ByBeUserIdAndTypeInOrderByHasReadDescIdDesc(Long receiveUserId, List<String> types);

    List<NotifyDO> findTop20ByBeUserIdAndTypeOrderByHasReadDescIdDesc(Long receiveUserId, String type);


    List<NotifyDO> findAllByBeUserIdAndTypeInAndHasReadFalseOrderByIdDesc(Long receiveUserId, List<String> types);
    List<NotifyDO> findAllByBeUserIdAndTypeAndHasReadFalseOrderByIdDesc(Long receiveUserId, String types);
}