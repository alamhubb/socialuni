package com.socialuni.sdk.dao.repository;

import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserImgDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialuniUserImgRepository extends JpaRepository<SocialuniUserImgDO, Integer> {
    @Cacheable(cacheNames = "getUserImgByUnionId", key = "#unionId")
    SocialuniUserImgDO findOneByUnionId(Integer unionId);

    @Cacheable(cacheNames = "getUserImgUnionIdsByUserIdTop6", key = "#userId")
    List<Integer> findUnionIdTop6ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    @Cacheable(cacheNames = "getUserImgUnionIdsByUserIdTop50", key = "#userId")
    List<Integer> findUnionIdTop50ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    //需要注意不能使用 @cachePut 上面是数组
    @CacheEvict(cacheNames = "getUserImgByUnionId", key = "#userImgDO.unionId")
    SocialuniUserImgDO save(SocialuniUserImgDO userImgDO);

    SocialUnionContentBaseDO findOneByUnionIdAndStatus(Integer unionId, String status);

    List<SocialuniUserImgDO> findAllByUnionIdIsNull();

    SocialuniUserImgDO getUserImgByUserIdAndSrc(Integer userId, String src);

    SocialuniUserImgDO getUserImgByUserIdAndUnionId(Integer userId, Integer unionId);

    //获取talkImg
    SocialuniUserImgDO findFirstBySrc(String imgUlr);
}

