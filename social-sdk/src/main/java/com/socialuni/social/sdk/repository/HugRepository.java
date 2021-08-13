package com.socialuni.social.sdk.repository;


import com.socialuni.entity.model.DO.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {
    Integer countByTalkIdAndUserId(Integer talkId, Integer userId);
}