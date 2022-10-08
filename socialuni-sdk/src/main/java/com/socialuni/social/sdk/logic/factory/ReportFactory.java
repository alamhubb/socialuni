package com.socialuni.social.sdk.logic.factory;

import com.socialuni.social.sdk.constant.ReportSourceType;
import com.socialuni.social.sdk.constant.socialuni.ReportStatus;
import com.socialuni.social.sdk.dao.DO.ReportDO;
import com.socialuni.social.sdk.dao.DO.SocialuniUnionIdDO;
import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReportFactory {
    public static ReportDO createReportDO(
            String reportSourceType,
            SocialUnionContentBaseDO socialuniContentBO,
            SocialuniUnionIdDO socialuniUnionIdDO
    ) {
        return ReportFactory.createReportDO(socialuniContentBO.getContentType(), socialuniContentBO.getUnionId(), socialuniContentBO.getContent(), socialuniContentBO.getUserId(), reportSourceType, socialuniUnionIdDO);
    }


    private static ReportDO createReportDO(
            String contentType,
            Integer contentId,
            String content,
            Integer contentUserId,
            String reportSourceType,
            SocialuniUnionIdDO socialuniUnionIdDO
    ) {
        // 设置model
        ReportDO reportDO = new ReportDO();

//        reportDO.setCause(cause);
        reportDO.setReportContent(content);
        //设置被举报用户
        reportDO.setContentUserId(contentUserId);
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
//        reportDO.setDevId(DevAccountUtils.getDevIdNotNull());
        //内容来源
        reportDO.setContentType(contentType);
        //举报来源
        reportDO.setReportSourceType(reportSourceType);

        reportDO.setContentId(contentId);
        //用来记录有哪些内容的审核权限，所以是内容归属的开发者
        reportDO.setDevId(socialuniUnionIdDO.getFromDevId());
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