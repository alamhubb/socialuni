package com.socialuni.sdk.dao.repository.community;

import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkImgRepository extends JpaRepository<SocialTalkImgDO, Integer> {
    @Cacheable(cacheNames = "talkImgsTalkId", key = "#talkId")
    List<SocialTalkImgDO> findTop3ByTalkId(Integer talkId);

    BaseModelDO findOneByUnionIdAndStatus(Integer unionId, String status);

    //获取talkImg
    SocialTalkImgDO findFirstBySrc(String imgUlr);

//    @CachePut(cacheNames = "talkImgsTalkId", key = "#imgs[0].talkId")
//    List<TalkImgDO> saveAll(List<TalkImgDO> imgs);
}
