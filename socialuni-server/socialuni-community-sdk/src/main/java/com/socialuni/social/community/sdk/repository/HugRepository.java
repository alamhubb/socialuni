package com.socialuni.social.community.sdk.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.community.sdk.dao.DO.SocialuniHugDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HugRepository extends JpaRepository<SocialuniHugDO, Integer> {
    default SocialuniHugDO savePut(SocialuniHugDO HugDO){
        return this.save(BeanUtil.toBean(HugDO, SocialuniHugDO.class));
    }
    SocialuniHugDO findByTalkIdAndUserId(Integer talkId, Long userId);
}