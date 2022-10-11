package com.socialuni.social.sdk.utils;

import com.socialuni.social.report.sdk.model.ReportDetailModel;
import com.socialuni.social.report.sdk.api.ReportDetailApi;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReportDetailUtils {
    private static ReportDetailApi reportDetailApi;

    @Resource
    public void setReportDetailRepository(ReportDetailApi reportDetailApi) {
        ReportDetailUtils.reportDetailApi = reportDetailApi;
    }

    public static List<?  extends ReportDetailModel> getAll(Integer reportId) {
        return reportDetailApi.findAllByReportId(reportId);
    }

    public static List<?  extends ReportDetailModel> saveAll(List<ReportDetailModel> reportDetailModels) {
        return reportDetailApi.savePutAll(reportDetailModels);
    }

    public static Integer count(Integer reportId) {
        return reportDetailApi.countByReportId(reportId);
    }
}
