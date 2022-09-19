package com.socialuni.sdk.dao.repository.community;

import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TalkImgRepository extends JpaRepository<SocialuniTalkImgDO, Integer> {
    @Cacheable(cacheNames = "talkImgsTalkId", key = "#talkId")
    List<SocialuniTalkImgDO> findTop3ByTalkId(Integer talkId);
/*    @Cacheable(cacheNames = "getTalkImgUnionIdsByTalkIdTop3", key = "#talkId")
    List<Integer> findUnionIdTop3ByTalkIdAndStatusInOrderByIdAsc(Integer talkId, List<String> status);*/

    @Cacheable(cacheNames = "getTalkImgUnionIdsByTalkIdTop3", key = "#talkId")
    @Query(nativeQuery = true, value = "select t.union_id from s_talk_img t where t.talk_id =:talkId order by t.id asc limit 3")
    List<SocialuniTalkImgDO> findUnionIdTop3ByTalkIdOrderByIdAsc(Integer talkId);

    @Cacheable(cacheNames = "getTalkImgByUnionId", key = "#unionId")
    SocialuniTalkImgDO findOneByUnionId(Integer unionId);

    SocialUnionContentBaseDO findOneByUnionIdAndStatus(Integer unionId, String status);

    //获取talkImg
    SocialuniTalkImgDO findFirstBySrc(String imgUlr);

//    @CachePut(cacheNames = "talkImgsTalkId", key = "#imgs[0].talkId")
//    List<TalkImgDO> saveAll(List<TalkImgDO> imgs);
}
