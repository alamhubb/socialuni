package com.socialuni.social.community.dev.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.community.dev.entity.HugDO;
import com.socialuni.social.community.sdk.api.HugApi;
import com.socialuni.social.community.sdk.model.HugModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends HugApi, JpaRepository<HugDO, Integer> {
    default HugModel savePut(HugModel hugModel){
        return this.save(BeanUtil.toBean(hugModel,HugDO.class));
    }
    HugModel findByTalkIdAndUserId(Integer talkId, Integer userId);
}