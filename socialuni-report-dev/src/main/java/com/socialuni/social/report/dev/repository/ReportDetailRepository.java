package com.socialuni.social.report.dev.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.report.dev.entity.ReportDO;
import com.socialuni.social.report.dev.entity.ReportDetailDO;
import com.socialuni.social.report.sdk.api.ReportDetailApi;
import com.socialuni.social.report.sdk.model.ReportDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:21
 */
public interface ReportDetailRepository extends ReportDetailApi,JpaRepository<ReportDetailDO, Integer> {
    default ReportDetailModel savePut(ReportDetailModel reportDetailModel){
        return this.save(BeanUtil.toBean(reportDetailModel,ReportDetailDO.class));
    }
    default List<?  extends ReportDetailModel> savePutAll(List<ReportDetailModel> reportDetailModels){
        for (ReportDetailModel reportDetailModel : reportDetailModels) {
            this.savePut(reportDetailModel);
        }
        return reportDetailModels;
    }
    //今天是否已经举报被审核了10个，不为待审核的有多少个今天
    Integer countByUserIdAndStatusNotAndCreateTimeBetween(Integer userId, String status, Date startDate, Date endDate);

    Integer countByUserIdAndCreateTimeBetween(Integer userId, Date startDate, Date endDate);

    Integer countByUserIdAndReportId(Integer userId, ReportDO reportDO);

    Integer countByReportId(Integer reportId);

    List<ReportDetailDO> findAllByReportId(Integer reportId);
}
