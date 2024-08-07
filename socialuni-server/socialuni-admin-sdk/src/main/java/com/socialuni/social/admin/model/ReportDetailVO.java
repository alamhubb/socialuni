package com.socialuni.social.admin.model;

import com.socialuni.social.report.sdk.entity.ReportDetailDO;
import lombok.Data;

@Data
public class ReportDetailVO {
// 举报时的备注信息


    private String content;
    private String reportType;

    public ReportDetailVO() {
    }

    public ReportDetailVO(ReportDetailDO ReportDetailDO) {
        this.content = ReportDetailDO.getContent();
        this.reportType = ReportDetailDO.getReportType();
    }
}
