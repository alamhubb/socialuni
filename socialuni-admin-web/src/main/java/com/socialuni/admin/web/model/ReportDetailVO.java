package com.socialuni.admin.web.model;

import com.socialuni.social.report.sdk.model.ReportDetailModel;
import lombok.Data;

@Data
public class ReportDetailVO {
// 举报时的备注信息


    private String content;
    private String reportType;

    public ReportDetailVO() {
    }

    public ReportDetailVO(ReportDetailModel reportDetailModel) {
        this.content = reportDetailModel.getContent();
        this.reportType = reportDetailModel.getReportType();
    }
}
