package com.socialuni.social.report.sdk.dao.store;

import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.content.constant.ReportStatus;
import com.socialuni.social.report.sdk.dao.repository.ReportRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReportStore {
    @Resource
    ReportRepository reportApi;

    public List<?  extends ReportDO> queryUserOtherWaitAuditContent(Long userId) {
        return reportApi.findByContentUserIdAndStatusIn(userId, ReportStatus.auditStatus);
    }
}
