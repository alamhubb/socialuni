package com.socialuni.social.sdk.repository;

import com.socialuni.entity.model.DO.base.BaseModelDO;
import com.socialuni.entity.model.DO.user.UserImgDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserImgRepository extends JpaRepository<UserImgDO, Integer> {

    @Cacheable(cacheNames = "getUserImgByUserId", key = "#userId")
    List<UserImgDO> findTop3ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    //需要注意不能使用 @cachePut 上面是数组
    @CacheEvict(cacheNames = "getUserImgByUserId", key = "#userImgDO.userId")
    UserImgDO save(UserImgDO userImgDO);

    Optional<BaseModelDO> findOneByIdAndStatus(Integer id, String status);

    UserImgDO getUserImgByUserIdAndSrc(Integer userId, String src);

    Optional<UserImgDO> getUserImgByUserIdAndId(Integer userId, Integer id);
}

