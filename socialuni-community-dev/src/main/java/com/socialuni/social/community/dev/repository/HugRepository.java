package com.socialuni.social.community.dev.repository;


import com.socialuni.social.community.dev.entity.HugDO;
import com.socialuni.social.community.sdk.model.HugModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {

    HugModel findByTalkIdAndUserId(Integer talkId, Integer userId);
}