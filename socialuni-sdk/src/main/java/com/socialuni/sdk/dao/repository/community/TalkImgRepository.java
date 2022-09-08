package com.socialuni.sdk.dao.repository.community;

import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkImgRepository extends JpaRepository<SocialTalkImgDO, Integer> {
    @Cacheable(cacheNames = "talkImgsTalkId", key = "#contentId")
    List<SocialTalkImgDO> findTop3ByContentId(Integer contentId);

    //获取talkImg
    SocialTalkImgDO findFirstBySrc(String imgUlr);

//    @CachePut(cacheNames = "talkImgsTalkId", key = "#imgs[0].talkId")
//    List<TalkImgDO> saveAll(List<TalkImgDO> imgs);
}
