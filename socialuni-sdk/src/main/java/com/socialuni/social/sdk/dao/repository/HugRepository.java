package com.socialuni.social.sdk.dao.repository;


import com.socialuni.social.sdk.dao.DO.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {

    HugDO findByTalkIdAndUserId(Integer talkId, Integer userId);
}