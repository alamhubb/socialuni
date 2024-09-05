package com.socialuni.social.report.sdk.dao.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.report.sdk.entity.ReportDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:21
 */
public interface ReportDetailRepository extends JpaRepository<ReportDetailDO, Integer> {
    default ReportDetailDO savePut(ReportDetailDO ReportDetailDO){
        return this.save(BeanUtil.toBean(ReportDetailDO,ReportDetailDO.class));
    }
    default List<?  extends ReportDetailDO> savePutAll(List<ReportDetailDO> ReportDetailDOs){
        for (ReportDetailDO ReportDetailDO : ReportDetailDOs) {
            this.savePut(ReportDetailDO);
        }
        return ReportDetailDOs;
    }
    //今天是否已经举报被审核了10个，不为待审核的有多少个今天
    Integer countByUserIdAndStatusNotAndCreateTimeBetween(Long userId, String status, Date startDate, Date endDate);

    Integer countByUserIdAndCreateTimeBetween(Long userId, Date startDate, Date endDate);

    Integer countByUserIdAndReportId(Long userId, ReportDO reportDO);

    Integer countByReportId(Long reportId);

    List<ReportDetailDO> findAllByReportId(Long reportId);
}
