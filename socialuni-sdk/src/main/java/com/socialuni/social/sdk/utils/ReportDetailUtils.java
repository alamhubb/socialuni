package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.dao.DO.ReportDetailDO;
import com.socialuni.social.sdk.dao.repository.ReportDetailRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReportDetailUtils {
    private static ReportDetailRepository reportDetailRepository;

    @Resource
    public void setReportDetailRepository(ReportDetailRepository reportDetailRepository) {
        ReportDetailUtils.reportDetailRepository = reportDetailRepository;
    }

    public static List<ReportDetailDO> getAll(Integer reportId) {
        return reportDetailRepository.findAllByReportId(reportId);
    }

    public static List<ReportDetailDO> saveAll(List<ReportDetailDO> reportDetailDOS) {
        return reportDetailRepository.saveAll(reportDetailDOS);
    }

    public static Integer count(Integer reportId) {
        return reportDetailRepository.countByReportId(reportId);
    }
}
