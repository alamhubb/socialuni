package com.socialuni.social.sdk.dao.repository.community;

import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgModel;
import com.socialuni.social.common.sdk.entity.SocialuniUnionContentBaseDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TalkImgRepository extends JpaRepository<SocialuniTalkImgModel, Integer> {
    @Caching(
            evict = {@CacheEvict(cacheNames = "getTalkImgUnionIdsByTalkIdTop3", key = "#talkImgDO.talkId")},
            put = {@CachePut(cacheNames = "getTalkImgByUnionId", key = "#talkImgDO.unionId")}
    )
    SocialuniTalkImgModel save(SocialuniTalkImgModel talkImgDO);

    @Cacheable(cacheNames = "talkImgsTalkId", key = "#talkId")
    List<SocialuniTalkImgModel> findTop3ByTalkId(Integer talkId);
/*    @Cacheable(cacheNames = "getTalkImgUnionIdsByTalkIdTop3", key = "#talkId")
    List<Integer> findUnionIdTop3ByTalkIdAndStatusInOrderByIdAsc(Integer talkId, List<String> status);*/

    @Cacheable(cacheNames = "getTalkImgUnionIdsByTalkIdTop3", key = "#talkId")
    @Query(nativeQuery = true, value = "select t.* from s_talk_img t where t.talk_id =:talkId order by t.id asc limit 3")
    List<SocialuniTalkImgModel> findUnionIdTop3ByTalkIdOrderByIdAsc(Integer talkId);

    @Cacheable(cacheNames = "getTalkImgByUnionId", key = "#unionId")
    SocialuniTalkImgModel findOneByUnionId(Integer unionId);

    SocialuniUnionContentBaseDO findOneByUnionIdAndStatus(Integer unionId, String status);

    //获取talkImg
    SocialuniTalkImgModel findFirstBySrc(String imgUlr);

//    @CachePut(cacheNames = "talkImgsTalkId", key = "#imgs[0].talkId")
//    List<TalkImgDO> saveAll(List<TalkImgDO> imgs);
}
