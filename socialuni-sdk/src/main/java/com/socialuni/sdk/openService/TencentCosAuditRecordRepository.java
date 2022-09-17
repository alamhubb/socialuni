package com.socialuni.sdk.openService;


import com.socialuni.sdk.dao.DO.TencentCosAuditRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TencentCosAuditRecordRepository extends JpaRepository<TencentCosAuditRecordDO, Integer> {
    List<TencentCosAuditRecordDO> findTop20ByStatusNotAndScoreGreaterThanEqualAndForbiddenStatusAndImgThumbTypeAndUserIdNotNullAndContentTypeOrderByIdDesc(String status, Integer score, Integer forbidStatus, String imgThumbType, String contentType);
}
