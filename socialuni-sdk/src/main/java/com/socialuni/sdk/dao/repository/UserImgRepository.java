package com.socialuni.sdk.dao.repository;

import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserImgRepository extends JpaRepository<SocialUserImgDO, Integer> {

    @Cacheable(cacheNames = "getUserImgByUserId", key = "#userId")
    List<SocialUserImgDO> findTop6ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    @Cacheable(cacheNames = "getUserImgByUserIdTop50", key = "#userId")
    List<SocialUserImgDO> findTop50ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    //需要注意不能使用 @cachePut 上面是数组
    @CacheEvict(cacheNames = "getUserImgByUserId", key = "#userImgDO.userId")
    SocialUserImgDO save(SocialUserImgDO userImgDO);

    BaseModelDO findOneByUnionIdAndStatus(Integer unionId, String status);
    BaseModelDO findOneByUnionId(Integer unionId);

    List<SocialUserImgDO> findAllByUnionIdIsNull();

    SocialUserImgDO getUserImgByUserIdAndSrc(Integer userId, String src);

    SocialUserImgDO getUserImgByUserIdAndUnionId(Integer userId, Integer unionId);

    //获取talkImg
    SocialUserImgDO findFirstBySrc(String imgUlr);
}

