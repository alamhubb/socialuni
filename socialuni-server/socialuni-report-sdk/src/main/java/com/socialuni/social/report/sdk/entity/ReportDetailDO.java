package com.socialuni.social.report.sdk.entity;


import com.socialuni.social.content.constant.ReportStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 举报信息
 */
@Entity
@Data
@Table(name = "s_report_detail")
public class ReportDetailDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long userId;
    //应该就是contentId
    private Long contentId;

    private Long receiveUserId;

    /**
     * 举报类型，政治敏感，暴露，暴力等
     */
    private String reportType;

    private Date createTime;
    private Date updateTime;

    private Long reportId;

    /**
     * 举报时的备注信息
     */
    private String content;
    private String reportCause;

    /**
     * 举报的状态，待审核，已通过，未通过，和report状态完全联动
     */
    private String status;

    public ReportDetailDO() {
    }

    //    todo 这里有个坑 content 改为了 cause
    public ReportDetailDO(String reportCause, String reportType, ReportDO reportDO, String content, Long requestUserId) {
        this.reportCause = reportCause;

        this.contentId = reportDO.getContentId();
        this.content = content;
        this.reportId = Long.valueOf(reportDO.getId());
        this.reportType = reportType;
        this.userId = requestUserId;

        this.createTime = new Date();
        this.status = ReportStatus.audit;
    }
}
