package com.socialuni.social.community.dev.repository;

import com.socialuni.social.community.dev.entity.TagDO;
import com.socialuni.social.community.sdk.enumeration.TagRedisKey;
import com.socialuni.social.community.sdk.model.TagModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TagRepository extends JpaRepository<TagDO, Integer> {
    TagModel findByIdAndStatus(Integer tagId, String status);


    @Caching(
            put = {
                    @CachePut(cacheNames = TagRedisKey.tagByName, key = "#tagModel.name"),
            }
    )
    TagModel save(TagModel tagModel);

    @Cacheable(cacheNames = TagRedisKey.tagByName, key = "#name")
    TagModel findFirstByName(String name);

    @Cacheable(cacheNames = TagRedisKey.tagByDevId, key = "#devId")
    TagModel findFirstByDevId(Integer devId);

    @Query("select t.id from TagDO t,SocialTalkTagDO tt where t.id = tt.tagId and tt.talkId =:talkId and t.status =:status and t.showFront = :showFront")
    List<Integer> findTagIdsByTalkIdAndStatusAndShowFront(Integer talkId, String status, Boolean showFront);

    List<?  extends TagModel> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    List<?  extends TagModel> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<?  extends TagModel> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);


    //查询热门前10tag
    List<?  extends TagModel> findByStatusOrderByCountDesc(String status, Pageable pageable);

    List<?  extends TagModel> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender, Pageable pageable);


    //查询所有tag
    List<?  extends TagModel> findAllByStatusOrderByCountDesc(String status);

    List<?  extends TagModel> findAllByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);
}
