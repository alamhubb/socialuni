package com.socialuni.admin.web.repository;


import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TencentCosAuditRecordRepository extends JpaRepository<TencentCosAuditRecordDO, Integer> {
    List<TencentCosAuditRecordDO> findTop200ByStatusNotAndScoreGreaterThanEqualAndForbiddenStatusAndImgThumbTypeAndUserIdNotNullOrderByIdDesc(String status,Integer score,  Integer forbidStatus, String imgThumbType);
}
