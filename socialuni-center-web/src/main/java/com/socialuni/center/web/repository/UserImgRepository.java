package com.socialuni.center.web.repository;

import com.socialuni.center.web.model.DO.base.BaseModelDO;
import com.socialuni.center.web.model.DO.user.SocialUserImgDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserImgRepository extends JpaRepository<SocialUserImgDO, Integer> {

    @Cacheable(cacheNames = "getUserImgByUserId", key = "#userId")
    List<SocialUserImgDO> findTop3ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    //需要注意不能使用 @cachePut 上面是数组
    @CacheEvict(cacheNames = "getUserImgByUserId", key = "#userImgDO.userId")
    SocialUserImgDO save(SocialUserImgDO userImgDO);

    BaseModelDO findOneByIdAndStatus(Integer id, String status);

    SocialUserImgDO getUserImgByUserIdAndSrc(Integer userId, String src);

    SocialUserImgDO getUserImgByUserIdAndId(Integer userId, Integer id);

    //获取talkImg
    SocialUserImgDO findFirstBySrc(String imgUlr);
}
