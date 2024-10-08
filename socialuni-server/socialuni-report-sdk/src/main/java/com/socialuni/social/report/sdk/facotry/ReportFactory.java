package com.socialuni.social.report.sdk.facotry;

import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.common.sdk.constant.ReportSourceType;
import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.content.constant.ReportStatus;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReportFactory {
    public static ReportDO createReportDO(
            String reportSourceType,
            SocialuniUnionContentBaseDO socialuniContentBO,
            SocialuniUnionIdDo socialuniUnionIdDo
    ) {
        return ReportFactory.createReportDO(socialuniContentBO.getContentType(), socialuniContentBO.getUnionId(), socialuniContentBO.getContent(), socialuniContentBO.getUserId(), reportSourceType, socialuniUnionIdDo);
    }


    private static ReportDO createReportDO(
            String contentType,
            Long contentId,
            String content,
            Long contentUserId,
            String reportSourceType,
            SocialuniUnionIdDo socialuniUnionIdDo
    ) {
        // 设置model
        ReportDO ReportDO = new ReportDO();

//        reportDO.setCause(cause);
        ReportDO.setReportContent(content);
        //设置被举报用户
        ReportDO.setContentUserId(contentUserId);
        if (ReportSourceType.userReport.equals(reportSourceType)) {
            ReportDO.setStatus(ReportStatus.audit);
        } else {
            ReportDO.setStatus(ReportStatus.preAudit);
        }
        //第一次被举报设置默认值
        ReportDO.setSupportRatio(100);
        ReportDO.setSupportRatio(0);
        ReportDO.setHasReview(false);
        Date curDate = new Date();
        ReportDO.setCreateTime(curDate);
        ReportDO.setUpdateTime(curDate);
//        reportDO.setDevId(DevAccountUtils.getDevIdNotNull());
        //内容来源
        ReportDO.setContentType(contentType);
        //举报来源
        ReportDO.setReportSourceType(reportSourceType);

        ReportDO.setContentId(contentId);
        //用来记录有哪些内容的审核权限，所以是内容归属的开发者
        ReportDO.setDevId(socialuniUnionIdDo.getFromDevId());
        return ReportDO;
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