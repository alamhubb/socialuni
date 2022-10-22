package com.socialuni.social.sdk.logic.factory;

import com.socialuni.social.sdk.constant.ReportSourceType;
import com.socialuni.social.report.sdk.enumeration.ReportStatus;
import com.socialuni.social.report.sdk.model.ReportModel;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReportFactory {
    public static ReportModel createReportDO(
            String reportSourceType,
            SocialUnionContentBaseDO socialuniContentBO,
            SocialuniUnionIdModler socialuniUnionIdModler
    ) {
        return ReportFactory.createReportDO(socialuniContentBO.getContentType(), socialuniContentBO.getUnionId(), socialuniContentBO.getContent(), socialuniContentBO.getUserId(), reportSourceType, socialuniUnionIdModler);
    }


    private static ReportModel createReportDO(
            String contentType,
            Integer contentId,
            String content,
            Integer contentUserId,
            String reportSourceType,
            SocialuniUnionIdModler socialuniUnionIdModler
    ) {
        // 设置model
        ReportModel reportModel = new ReportModel();

//        reportDO.setCause(cause);
        reportModel.setReportContent(content);
        //设置被举报用户
        reportModel.setContentUserId(contentUserId);
        if (ReportSourceType.userReport.equals(reportSourceType)) {
            reportModel.setStatus(ReportStatus.audit);
        } else {
            reportModel.setStatus(ReportStatus.preAudit);
        }
        //第一次被举报设置默认值
        reportModel.setSupportRatio(100);
        reportModel.setSupportRatio(0);
        reportModel.setHasReview(false);
        Date curDate = new Date();
        reportModel.setCreateTime(curDate);
        reportModel.setUpdateTime(curDate);
//        reportDO.setDevId(DevAccountUtils.getDevIdNotNull());
        //内容来源
        reportModel.setContentType(contentType);
        //举报来源
        reportModel.setReportSourceType(reportSourceType);

        reportModel.setContentId(contentId);
        //用来记录有哪些内容的审核权限，所以是内容归属的开发者
        reportModel.setDevId(socialuniUnionIdModler.getFromDevId());
        return reportModel;
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