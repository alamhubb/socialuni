package com.socialuni.social.sdk.dao.store;

import com.socialuni.social.report.sdk.enumeration.ReportStatus;
import com.socialuni.social.report.sdk.model.ReportModel;
import com.socialuni.social.report.sdk.api.ReportApi;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReportStore {
    @Resource
    ReportApi reportApi;

    public List<?  extends ReportModel> queryUserOtherWaitAuditContent(Integer userId) {
        return reportApi.findByContentUserIdAndStatusIn(userId, ReportStatus.auditStatus);
    }
}
