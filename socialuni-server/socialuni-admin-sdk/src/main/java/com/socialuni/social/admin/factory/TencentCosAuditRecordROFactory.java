package com.socialuni.social.admin.factory;

import com.socialuni.social.admin.model.RO.TencentCosAuditRecordRO;
import com.socialuni.social.common.sdk.dao.DO.TencentCosAuditRecordDO;

public class TencentCosAuditRecordROFactory {
    public static TencentCosAuditRecordRO getTencentCosAuditRecordRO(TencentCosAuditRecordDO recordDO) {
        TencentCosAuditRecordRO record = new TencentCosAuditRecordRO(recordDO);

        return record;
    }
}
