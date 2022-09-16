package com.socialuni.sdk.dao.store;

import com.socialuni.sdk.constant.socialuni.ReportStatus;
import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.repository.ReportRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReportStore {
    @Resource
    ReportRepository reportRepository;

    public List<ReportDO> queryUserOtherWaitAuditContent(Integer userId) {
        return reportRepository.findByReceiveUserIdAndStatusIn(userId, ReportStatus.auditStatus);
    }
}