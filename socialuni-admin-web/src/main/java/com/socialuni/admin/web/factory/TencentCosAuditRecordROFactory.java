package com.socialuni.admin.web.factory;

import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.admin.web.model.RO.TencentCosAuditRecordRO;

public class TencentCosAuditRecordROFactory {
    public static TencentCosAuditRecordRO getTencentCosAuditRecordRO(TencentCosAuditRecordDO recordDO) {
        TencentCosAuditRecordRO record = new TencentCosAuditRecordRO(recordDO);

        return record;
    }
}
