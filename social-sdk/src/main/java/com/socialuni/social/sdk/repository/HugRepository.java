package com.socialuni.social.sdk.repository;


import com.socialuni.social.entity.model.DO.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {

    HugDO findByTalkIdAndUserId(Integer talkId, Integer userId);
}