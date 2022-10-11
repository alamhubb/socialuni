package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import com.socialuni.social.user.sdk.api.SocialuniUserImgApi;
import com.socialuni.social.user.sdk.entity.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.model.SocialuniUserImgModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUserImgRepository extends SocialuniUserImgApi, JpaRepository<SocialuniUserImgDo, Integer> {
    @Cacheable(cacheNames = "getUserImgByUnionId", key = "#unionId")
    SocialuniUserImgModel findOneByUnionId(Integer unionId);

    @Cacheable(cacheNames = "getUserImgUnionIdsByUserIdTop6", key = "#userId")
    @Query(nativeQuery = true, value = "select t.union_id from s_user_img t where t.user_id =:userId and t.status in (:status) order by t.create_time desc limit 6")
    List<Integer> findUnionIdTop6ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    @Cacheable(cacheNames = "getUserImgUnionIdsByUserIdTop50", key = "#userId")
    @Query(nativeQuery = true, value = "select t.union_id from s_user_img t where t.user_id =:userId and t.status in (:status) order by t.create_time desc limit 50")
    List<Integer> findUnionIdTop50ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "getUserImgUnionIdsByUserIdTop6", key = "#userImgDO.userId"),
                    @CacheEvict(cacheNames = "getUserImgUnionIdsByUserIdTop50", key = "#userImgDO.userId")
            },
            put = {@CachePut(cacheNames = "getUserImgByUnionId", key = "#userImgDO.unionId")}
    )
    default SocialuniUserImgModel savePut(SocialuniUserImgModel userImgDO){
        return this.save(BeanUtil.toBean(userImgDO,SocialuniUserImgDo.class));
    }

    SocialUnionContentBaseDO findOneByUnionIdAndStatus(Integer unionId, String status);

    List<SocialuniUserImgModel> findAllByUnionIdIsNull();

    SocialuniUserImgModel getUserImgByUserIdAndSrc(Integer userId, String src);

    SocialuniUserImgModel getUserImgByUserIdAndUnionId(Integer userId, Integer unionId);

    //获取talkImg
    SocialuniUserImgModel findFirstBySrc(String imgUlr);
}

