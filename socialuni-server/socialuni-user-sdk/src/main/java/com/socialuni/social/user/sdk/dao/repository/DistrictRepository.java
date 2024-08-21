package com.socialuni.social.user.sdk.dao.repository;

import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<SocialuniDistrictDO, Integer> {
    Optional<SocialuniDistrictDO> findFirstOneByAdCode(String adCode);

    List<SocialuniDistrictDO> findByParentAdCodeAndStatusOrderByAdCode(String parentAdCode, String status);

    //暂时按照排序，以后根据热度，热度算法还没有
    List<SocialuniDistrictDO> findTop20ByDistrictCodeAndStatusOrderByCountDesc(String districtCode, String status);
}