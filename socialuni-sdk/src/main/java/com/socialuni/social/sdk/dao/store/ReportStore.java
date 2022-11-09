package com.socialuni.social.sdk.dao.store;

import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.report.sdk.enumeration.ReportStatus;
import com.socialuni.social.report.sdk.repository.ReportRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReportStore {
    @Resource
    ReportRepository reportApi;

    public List<?  extends ReportDO> queryUserOtherWaitAuditContent(Integer userId) {
        return reportApi.findByContentUserIdAndStatusIn(userId, ReportStatus.auditStatus);
    }
}
