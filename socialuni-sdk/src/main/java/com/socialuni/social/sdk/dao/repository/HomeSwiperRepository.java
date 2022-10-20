package com.socialuni.social.sdk.dao.repository;

import com.socialuni.social.sdk.api.SocialuniHomeSwiperAPI;
import com.socialuni.social.sdk.dao.DO.HomeSwiperDO;
import com.socialuni.social.sdk.model.model.SocialuniHomeSwiperModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeSwiperRepository extends JpaRepository<HomeSwiperDO, Integer>, SocialuniHomeSwiperAPI {
    @Cacheable(cacheNames = "homeSwipersByDevId")
    List<HomeSwiperDO> findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(String status, Integer devId);

    @Cacheable(cacheNames = "homeSwipers", key = "#status")
    List<HomeSwiperDO> findAllByStatusOrderByTopLevelAscIdDesc(String status);

    List<SocialuniHomeSwiperModel> queryHomeSwipersByDevIdOrderByStatusDescUpdateTimeDescTopLevelDesc(Integer devId);
}

