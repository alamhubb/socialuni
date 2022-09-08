package com.socialuni.sdk.dao.repository;


import com.socialuni.sdk.dao.DO.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {

    HugDO findByTalkIdAndUserId(Integer talkId, Integer userId);
}