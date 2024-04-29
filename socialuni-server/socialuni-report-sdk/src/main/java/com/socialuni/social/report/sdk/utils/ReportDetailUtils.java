package com.socialuni.social.report.sdk.utils;

import com.socialuni.social.report.sdk.entity.ReportDetailDO;
import com.socialuni.social.report.sdk.dao.repository.ReportDetailRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReportDetailUtils {
    private static ReportDetailRepository reportDetailApi;

    @Resource
    public void setReportDetailRepository(ReportDetailRepository reportDetailApi) {
        ReportDetailUtils.reportDetailApi = reportDetailApi;
    }

    public static List<?  extends ReportDetailDO> getAll(Integer reportId) {
        return reportDetailApi.findAllByReportId(reportId);
    }

    public static List<?  extends ReportDetailDO> saveAll(List<ReportDetailDO> ReportDetailDOs) {
        return reportDetailApi.savePutAll(ReportDetailDOs);
    }

    public static Integer count(Integer reportId) {
        return reportDetailApi.countByReportId(reportId);
    }
}
