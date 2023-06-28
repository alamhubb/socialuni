package com.socialuni.social.im.dao.repository;


import com.socialuni.social.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.user.sdk.model.DO.AuthenticationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SocialuniFriendApplyRecordRepository extends JpaRepository<SocialuniFriendApplyRecordDO, Integer> {
    @Query(nativeQuery = true, value = "select distinct t.* from s_user_friend_apply_record t where (t.user_id=:userId or t.be_user_id = :userId) and t.type = :type GROUP BY t.user_id desc limit 30")
    List<SocialuniFriendApplyRecordDO> findTop30ByUserIdOrBeUserIdAndType(Integer userId, String type);
}

