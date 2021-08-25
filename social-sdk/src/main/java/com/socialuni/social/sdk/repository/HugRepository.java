package com.socialuni.social.sdk.repository;


import com.socialuni.social.entity.model.DO.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {
    //关注后用户缓存修改，一人+粉丝，一人+关注
    HugDO save(HugDO hugDO);

    HugDO findByTalkIdAndUserId(Integer talkId, Integer userId);
}