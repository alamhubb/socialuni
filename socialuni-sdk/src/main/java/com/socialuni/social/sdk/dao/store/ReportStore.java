package com.socialuni.social.sdk.dao.store;

import com.socialuni.social.sdk.constant.socialuni.ReportStatus;
import com.socialuni.social.sdk.dao.DO.ReportDO;
import com.socialuni.social.sdk.dao.repository.ReportRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReportStore {
    @Resource
    ReportRepository reportRepository;

    public List<ReportDO> queryUserOtherWaitAuditContent(Integer userId) {
        return reportRepository.findByContentUserIdAndStatusIn(userId, ReportStatus.auditStatus);
    }
}
