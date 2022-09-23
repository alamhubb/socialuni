package com.socialuni.social.sdk.dao.repository;

import com.socialuni.social.sdk.dao.DO.DistrictDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<DistrictDO, Integer> {
    Optional<DistrictDO> findFirstOneByAdCode(String adCode);

    List<DistrictDO> findByParentAdCodeAndStatusOrderByAdCode(String parentAdCode, String status);

    //暂时按照排序，以后根据热度，热度算法还没有
    List<DistrictDO> findTop20ByDistrictCodeAndStatusOrderByCountDesc(String districtCode, String status);
}