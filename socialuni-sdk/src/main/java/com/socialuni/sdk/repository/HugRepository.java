package com.socialuni.sdk.repository;


import com.socialuni.sdk.model.DO.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {

    HugDO findByTalkIdAndUserId(Integer talkId, Integer userId);
}