package com.socialuni.sdk.dao.repository.community;

import com.socialuni.sdk.dao.DO.community.talk.SocialTalkCircleDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
public interface SocialTalkCircleRepository extends JpaRepository<SocialTalkCircleDO, Integer> {
    @Cacheable(cacheNames = "getTalkCircleInfo", key = "#talkId")
    SocialTalkCircleDO findFirstByTalkId(Integer talkId);
}