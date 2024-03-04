package com.socialuni.social.community.sdk.repository;

import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkImgDO;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TalkImgRepository extends JpaRepository<SocialuniTalkImgDO, Integer> {
    @Caching(
            evict = {@CacheEvict(cacheNames = "getTalkImgUnionIdsByTalkIdTop3", key = "#talkImgDO.talkId")},
            put = {@CachePut(cacheNames = "getTalkImgByUnionId", key = "#talkImgDO.unionId")}
    )
    SocialuniTalkImgDO save(SocialuniTalkImgDO talkImgDO);

    @Cacheable(cacheNames = "talkImgsTalkId", key = "#talkId")
    List<SocialuniTalkImgDO> findTop3ByTalkId(Integer talkId);
/*    @Cacheable(cacheNames = "getTalkImgUnionIdsByTalkIdTop3", key = "#talkId")
    List<Integer> findUnionIdTop3ByTalkIdAndStatusInOrderByIdAsc(Integer talkId, List<String> status);*/

    @Cacheable(cacheNames = "getTalkImgUnionIdsByTalkIdTop3", key = "#talkId")
    @Query(nativeQuery = true, value = "select t.* from s_community_talk_img t where t.talk_id =:talkId order by t.id asc limit 3")
    List<SocialuniTalkImgDO> findUnionIdTop3ByTalkIdOrderByIdAsc(Integer talkId);

    @Cacheable(cacheNames = "getTalkImgByUnionId", key = "#unionId")
    SocialuniTalkImgDO findOneByUnionId(Integer unionId);

    SocialuniUnionContentBaseDO findOneByUnionIdAndStatus(Integer unionId, String status);

    //获取talkImg
    SocialuniTalkImgDO findFirstBySrc(String imgUlr);

//    @CachePut(cacheNames = "talkImgsTalkId", key = "#imgs[0].talkId")
//    List<TalkImgDO> saveAll(List<TalkImgDO> imgs);
}
