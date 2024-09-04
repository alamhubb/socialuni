package com.socialuni.social.tag.dao.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.tag.dao.DO.SocialuniTagDO;
import com.socialuni.social.tag.dao.redis.TagRedisKey;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;


public interface TagRepository extends JpaRepository<SocialuniTagDO, Integer> {
    SocialuniTagDO findByIdAndStatus(Integer tagId, String status);
    default  List<SocialuniTagDO> savePutAll(List<SocialuniTagDO> TagDOs){
        List<SocialuniTagDO> list = new ArrayList<>();
        for (SocialuniTagDO TagDO : TagDOs) {
            SocialuniTagDO save = this.save(BeanUtil.toBean(TagDO, SocialuniTagDO.class));
            list.add(save);
        }
        return list;
    }
    default SocialuniTagDO savePut(SocialuniTagDO TagDO){
        return this.save(BeanUtil.toBean(TagDO, SocialuniTagDO.class));
    }
    @Caching(
            put = {
                    @CachePut(cacheNames = TagRedisKey.tagByName, key = "#TagDO.name"),
            }
    )
    SocialuniTagDO save(SocialuniTagDO TagDO);



    @Cacheable(cacheNames = TagRedisKey.tagByName, key = "#name")
    SocialuniTagDO findFirstByName(String name);

    @Cacheable(cacheNames = TagRedisKey.tagByDevId, key = "#devId")
    SocialuniTagDO findFirstByDevId(Integer devId);

    @Query("select t.id from SocialuniTagDO t,SocialTalkTagDO tt where t.id = tt.tagId and tt.talkId =:talkId and t.status =:status and t.showFront = :showFront")
    List<Integer> findTagIdsByTalkIdAndStatusAndShowFront(Long talkId, String status, Boolean showFront);

    List<SocialuniTagDO> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    List<SocialuniTagDO> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<SocialuniTagDO> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);


    //查询热门前10tag
    List<SocialuniTagDO> findByStatusOrderByCountDesc(String status, Pageable pageable);

    List<SocialuniTagDO> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender, Pageable pageable);


    //查询所有tag
    List<SocialuniTagDO> findAllByStatusOrderByCountDesc(String status);

    List<SocialuniTagDO> findAllByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);
}
