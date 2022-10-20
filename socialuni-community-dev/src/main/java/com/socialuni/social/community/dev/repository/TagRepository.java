package com.socialuni.social.community.dev.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.community.dev.entity.TagDO;
import com.socialuni.social.community.sdk.api.TagInterface;
import com.socialuni.social.community.sdk.enumeration.TagRedisKey;
import com.socialuni.social.community.sdk.model.TagModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;


public interface TagRepository extends TagInterface,JpaRepository<TagDO, Integer> {
    TagDO findByIdAndStatus(Integer tagId, String status);
    default  List<TagModel> savePutAll(List<TagModel> tagModels){
        List<TagModel> list = new ArrayList<>();
        for (TagModel tagModel : tagModels) {
            TagDO save = this.save(BeanUtil.toBean(tagModel, TagDO.class));
            list.add(save);
        }
        return list;
    }
    default TagModel savePut(TagModel tagModel){
        return this.save(BeanUtil.toBean(tagModel,TagDO.class));
    }
    @Caching(
            put = {
                    @CachePut(cacheNames = TagRedisKey.tagByName, key = "#tagModel.name"),
            }
    )
    TagDO save(TagDO tagModel);



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
