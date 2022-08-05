package com.socialuni.center.web.store;

import com.socialuni.social.constant.ReportStatus;
import com.socialuni.center.web.model.DO.ReportDO;
import com.socialuni.center.web.repository.ReportRepository;
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
