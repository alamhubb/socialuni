package com.socialuni.social.sdk.repository;

import com.socialuni.social.entity.model.DO.talk.SocialTalkImgDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkImgRepository extends JpaRepository<SocialTalkImgDO, Integer> {
    @Cacheable(cacheNames = "talkImgsTalkId", key = "#p0")
    List<SocialTalkImgDO> findTop3ByTalkId(Integer talkId);

//    @CachePut(cacheNames = "talkImgsTalkId", key = "#imgs[0].talkId")
//    List<TalkImgDO> saveAll(List<TalkImgDO> imgs);
}
