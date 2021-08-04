package com.socialuni.sdk.repository;


import com.socialuni.sdk.model.DO.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {
    Integer countByTalkIdAndUserId(Integer talkId, Integer userId);
}