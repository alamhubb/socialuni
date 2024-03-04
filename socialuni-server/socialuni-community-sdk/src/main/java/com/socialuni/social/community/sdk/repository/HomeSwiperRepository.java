package com.socialuni.social.community.sdk.repository;

import com.socialuni.social.community.sdk.dao.DO.SocialuniHomeSwiperDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeSwiperRepository extends JpaRepository<SocialuniHomeSwiperDO, Integer>{
    @Cacheable(cacheNames = "getHomeSwipersByDevId", key = "#devId")
    List<SocialuniHomeSwiperDO> findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(String status, Integer devId);

    @Cacheable(cacheNames = "homeSwipers", key = "#status")
    List<SocialuniHomeSwiperDO> findAllByStatusOrderByTopLevelAscIdDesc(String status);

    //供后台管理使用，无需缓存，根据开发者id获取所有轮播图，可以开启关闭修改
    List<SocialuniHomeSwiperDO> queryHomeSwipersByDevIdOrderByStatusDescUpdateTimeDescTopLevelDesc(Integer devId);

    @Caching(evict = {
            @CacheEvict(cacheNames = "getHomeSwipersByDevId", key = "#entity.devId")
    })
    SocialuniHomeSwiperDO save(SocialuniHomeSwiperDO entity);

    Optional<SocialuniHomeSwiperDO> findByIdAndDevId(Integer id, Integer devId);
}

