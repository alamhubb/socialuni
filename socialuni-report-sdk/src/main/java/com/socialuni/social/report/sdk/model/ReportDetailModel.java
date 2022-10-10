package com.socialuni.social.report.sdk.model;


import com.socialuni.social.report.sdk.enumeration.ReportStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 举报信息
 */
@Data
public class ReportDetailModel implements Serializable {

    private Integer id;

    private Integer userId;
    //应该就是contentId
    private Integer contentId;

    private Integer receiveUserId;

    /**
     * 举报类型，政治敏感，暴露，暴力等
     */
    private String reportType;

    private Date createTime;
    private Date updateTime;

    private Integer reportId;

    /**
     * 举报时的备注信息
     */
    private String content;
    private String reportCause;

    /**
     * 举报的状态，待审核，已通过，未通过，和report状态完全联动
     */
    private String status;

    public ReportDetailModel() {
    }

    //    todo 这里有个坑 content 改为了 cause
    public ReportDetailModel(String reportCause, String reportType, ReportModel reportModel, String content, Integer requestUserId) {
        this.reportCause = reportCause;

        this.contentId = reportModel.getContentId();
        this.content = content;
        this.reportId = reportModel.getId();
        this.reportType = reportType;
        this.userId = requestUserId;

        this.createTime = new Date();
        this.status = ReportStatus.audit;
    }
}
