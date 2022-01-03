package com.socialuni.admin.web.repository;


import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.center.sdk.model.DevAuthCodeDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TencentCosAuditRecordRepository extends JpaRepository<TencentCosAuditRecordDO, Integer> {

}
