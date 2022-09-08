package com.socialuni.sdk.dao.repository;

import com.socialuni.sdk.model.DO.HomeSwiperDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeSwiperRepository extends JpaRepository<HomeSwiperDO, Integer> {
    @Cacheable(cacheNames = "homeSwipersByDevId")
    List<HomeSwiperDO> findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(String status, Integer devId);
    @Cacheable(cacheNames = "homeSwipers",key = "#status")
    List<HomeSwiperDO> findAllByStatusOrderByTopLevelAscIdDesc(String status);
}

