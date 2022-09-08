package com.socialuni.sdk.dao.repository.community;

import com.socialuni.sdk.dao.redis.redisKey.TagRedisKey;
import com.socialuni.sdk.model.DO.tag.TagDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TagRepository extends JpaRepository<TagDO, Integer> {
    TagDO findByIdAndStatus(Integer tagId, String status);


    @Caching(
            put = {
                    @CachePut(cacheNames = TagRedisKey.tagByName, key = "#tagDO.name"),
            }
    )
    TagDO save(TagDO tagDO);

    @Cacheable(cacheNames = TagRedisKey.tagByName, key = "#name")
    TagDO findFirstByName(String name);

    @Cacheable(cacheNames = TagRedisKey.tagByDevId, key = "#devId")
    TagDO findFirstByDevId(Integer devId);

    @Query("select t.id from TagDO t,SocialTalkTagDO tt where t.id = tt.tagId and tt.talkId =:talkId and t.status =:status and t.showFront = :showFront")
    List<Integer> findTagIdsByTalkIdAndStatusAndShowFront(Integer talkId, String status, Boolean showFront);

    List<TagDO> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    List<TagDO> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<TagDO> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);


    //查询热门前10tag
    List<TagDO> findByStatusOrderByCountDesc(String status, Pageable pageable);

    List<TagDO> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender, Pageable pageable);


    //查询所有tag
    List<TagDO> findAllByStatusOrderByCountDesc(String status);

    List<TagDO> findAllByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);
}
