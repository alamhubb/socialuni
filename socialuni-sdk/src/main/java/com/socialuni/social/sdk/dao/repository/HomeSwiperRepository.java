package com.socialuni.social.sdk.dao.repository;

import com.socialuni.social.sdk.api.SocialuniHomeSwiperAPI;
import com.socialuni.social.sdk.dao.DO.HomeSwiperDO;
import com.socialuni.social.sdk.model.model.SocialuniHomeSwiperModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeSwiperRepository extends JpaRepository<HomeSwiperDO, Integer>, SocialuniHomeSwiperAPI {
    @Cacheable(cacheNames = "getHomeSwipersByDevId", key = "#devId")
    List<HomeSwiperDO> findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(String status, Integer devId);

    @Cacheable(cacheNames = "homeSwipers", key = "#status")
    List<HomeSwiperDO> findAllByStatusOrderByTopLevelAscIdDesc(String status);

    //供后台管理使用，无需缓存，根据开发者id获取所有轮播图，可以开启关闭修改
    List<SocialuniHomeSwiperModel> queryHomeSwipersByDevIdOrderByStatusDescUpdateTimeDescTopLevelDesc(Integer devId);

    @Caching(evict = {
            @CacheEvict(cacheNames = "getHomeSwipersByDevId", key = "#entity.devId")
    })
    HomeSwiperDO save(HomeSwiperDO entity);

    Optional<SocialuniHomeSwiperModel> findByIdAndDevId(Integer id, Integer devId);
}

