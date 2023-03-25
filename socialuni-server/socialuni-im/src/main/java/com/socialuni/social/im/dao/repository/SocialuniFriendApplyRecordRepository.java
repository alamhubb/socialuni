package com.socialuni.social.im.dao.repository;


import com.socialuni.social.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.user.sdk.model.DO.AuthenticationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SocialuniFriendApplyRecordRepository extends JpaRepository<SocialuniFriendApplyRecordDO, Integer> {

    /*@Query("select SocialuniFriendApplyRecordDO from SocialuniFriendApplyRecordDO s where (s.userId=:userId or s.beUserId = :userId) ")
    List<SocialuniFriendApplyRecordDO> findTop30ByUserIdAndStatus(Integer userId);*/


    List<SocialuniFriendApplyRecordDO> findTop30ByUserIdOrBeUserId(Integer userId, Integer beUserId);
}


