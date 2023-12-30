package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.model.DO.SocialuniUserExtendFriendLogDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserHugDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SocialuniUserExtendFriendLogRepository extends JpaRepository<SocialuniUserExtendFriendLogDo, Integer> {

    @Query("select s.userId from SocialuniUserExtendFriendLogDo s where s.updateTime>=:updateTime")
    List<Integer> findUserIdByUpdateTimeLessThan(Date updateTime);
}

