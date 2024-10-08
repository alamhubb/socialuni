package com.socialuni.social.userImg.dao.repository;

import com.socialuni.social.userImg.dao.DO.SocialuniUserImgDo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUserImgRepository extends JpaRepository<SocialuniUserImgDo, Integer> {
    @Cacheable(cacheNames = "getUserImgByUnionId", key = "#unionId")
    SocialuniUserImgDo findOneByUnionId(Long unionId);

    @Cacheable(cacheNames = "getUserImgUnionIdsByUserIdTop6", key = "#userId")
    @Query(nativeQuery = true, value = "select t.union_id from s_user_img t where t.user_id =:userId and t.status in (:status) order by t.create_time desc limit 6")
    List<Long> findUnionIdTop6ByUserIdAndStatusInOrderByCreateTimeDesc(Long userId, List<String> status);

    @Cacheable(cacheNames = "getUserImgUnionIdsByUserIdTop50", key = "#userId")
    @Query(nativeQuery = true, value = "select t.union_id from s_user_img t where t.user_id =:userId and t.status in (:status) order by t.create_time desc limit 50")
    List<Long> findUnionIdTop50ByUserIdAndStatusInOrderByCreateTimeDesc(Long userId, List<String> status);

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "getUserImgUnionIdsByUserIdTop6", key = "#userImgDO.userId"),
                    @CacheEvict(cacheNames = "getUserImgUnionIdsByUserIdTop50", key = "#userImgDO.userId")
            },
            put = {@CachePut(cacheNames = "getUserImgByUnionId", key = "#userImgDO.unionId")}
    )
    default SocialuniUserImgDo savePut(SocialuniUserImgDo userImgDO){
        return this.save(userImgDO);
    }

    SocialuniUserImgDo findOneByUnionIdAndStatus(Long unionId, String status);

    List<SocialuniUserImgDo> findAllByUnionIdIsNull();

    SocialuniUserImgDo getUserImgByUserIdAndSrc(Long userId, String src);

    SocialuniUserImgDo getUserImgByUserIdAndUnionId(Long userId, Long unionId);

    //获取talkImg
    SocialuniUserImgDo findFirstBySrc(String imgUlr);
}

