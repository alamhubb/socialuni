package com.socialuni.center.web.repository.community;

import com.socialuni.social.entity.model.DO.circle.SocialCircleDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SocialCircleRepository extends JpaRepository<SocialCircleDO, Integer> {

    //查询热门前10tag
    List<SocialCircleDO> findByStatusOrderByCountDesc(String status, Pageable pageable);

    SocialCircleDO findFirstByName(String name);

    @Cacheable(cacheNames = "getCircleInfo", key = "#circleId")
    SocialCircleDO findFirstByIdAndStatus(Integer circleId, String status);

    List<SocialCircleDO> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    List<SocialCircleDO> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<SocialCircleDO> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);
}
