package com.socialuni.social.report.sdk.api;

import com.socialuni.social.report.sdk.model.ReportDetailModel;
import com.socialuni.social.report.sdk.model.ReportModel;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:21
 */
public interface ReportDetailApi{
    //今天是否已经举报被审核了10个，不为待审核的有多少个今天
    Integer countByUserIdAndStatusNotAndCreateTimeBetween(Integer userId, String status, Date startDate, Date endDate);

    Integer countByUserIdAndCreateTimeBetween(Integer userId, Date startDate, Date endDate);

    Integer countByUserIdAndReportId(Integer userId, ReportModel reportModel);

    Integer countByReportId(Integer reportId);

    List<?  extends ReportDetailModel> findAllByReportId(Integer reportId);

    ReportDetailModel save(ReportDetailModel reportDetailModel);

    List<?  extends ReportDetailModel> saveAll(List<ReportDetailModel> reportDetailModels);
}
