package com.socialuni.social.sdk.store;

import com.socialuni.constant.ReportStatus;
import com.socialuni.entity.model.DO.ReportDO;
import com.socialuni.social.sdk.repository.ReportRepository;
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
