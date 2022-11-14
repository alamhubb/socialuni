package com.socialuni.social.community.sdk.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.community.sdk.entity.HugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<HugDO, Integer> {
    default HugDO savePut(HugDO HugDO){
        return this.save(BeanUtil.toBean(HugDO,HugDO.class));
    }
    HugDO findByTalkIdAndUserId(Integer talkId, Integer userId);
}