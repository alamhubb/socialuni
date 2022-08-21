package com.socialuni.admin.web.model;

import com.socialuni.sdk.model.DO.ReportDetailDO;
import lombok.Data;

@Data
public class ReportDetailVO {
// 举报时的备注信息


    private String content;
    private String reportType;

    public ReportDetailVO() {
    }

    public ReportDetailVO(ReportDetailDO reportDetailDO) {
        this.content = reportDetailDO.getContent();
        this.reportType = reportDetailDO.getReportType();
    }
}
