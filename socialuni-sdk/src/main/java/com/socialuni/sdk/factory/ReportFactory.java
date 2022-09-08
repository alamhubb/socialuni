package com.socialuni.sdk.factory;

import com.socialuni.sdk.constant.ReportSourceType;
import com.socialuni.sdk.model.DO.ReportDO;
import com.socialuni.sdk.model.DO.UniContentUnionIdDO;
import com.socialuni.sdk.model.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.repository.CommentRepository;
import com.socialuni.sdk.logic.service.ReportService;
import com.socialuni.sdk.constant.socialuni.ReportStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ReportFactory {
    @Resource
    private CommentRepository commentRepository;

    @Resource
    private ReportService reportService;

    public ReportDO createReportDO(
            String cause,
            UniContentUnionIdDO uniContentUnionIdDO,
            BaseModelDO model,
            String reportSourceType, Integer devId
    ) {
        // 设置model
        ReportDO reportDO = new ReportDO();

        reportDO.setCause(cause);
        if (model != null) {
            reportDO.setReportContent(model.getContent());
            //设置被举报用户
            reportDO.setReceiveUserId(model.getUserId());
        }
        if (ReportSourceType.userReport.equals(reportSourceType)) {
            reportDO.setStatus(ReportStatus.audit);
        } else {
            reportDO.setStatus(ReportStatus.preAudit);
        }
        //第一次被举报设置默认值
        reportDO.setSupportRatio(100);
        reportDO.setSupportRatio(0);
        reportDO.setHasReview(false);
        Date curDate = new Date();
        reportDO.setCreateTime(curDate);
        reportDO.setUpdateTime(curDate);
        reportDO.setDevId(devId);
        //内容来源
        reportDO.setReportContentType(uniContentUnionIdDO.getContentType());
        //举报来源
        reportDO.setReportSourceType(reportSourceType);

        reportDO.setContentId(uniContentUnionIdDO.getId());
        return reportDO;
    }

    /*public ReportDO createReportDO(
            String cause,
            BaseModelDO model,
            String reportSourceType,
            Integer antispamId
    ) {
        // 设置model
        ReportDO reportDO = this.createReportDO(cause, model, reportSourceType);
        reportDO.setAntispamId(antispamId);
        return reportDO;
    }*/
}