package com.socialuni.center.web.repository;


import com.socialuni.center.web.model.DO.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {

    HugDO findByTalkIdAndUserId(Integer talkId, Integer userId);
}