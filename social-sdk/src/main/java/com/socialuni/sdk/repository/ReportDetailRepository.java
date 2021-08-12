package com.socialuni.sdk.repository;

import com.socialuni.entity.model.DO.ReportDO;
import com.socialuni.entity.model.DO.ReportDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:21
 */
public interface ReportDetailRepository extends JpaRepository<ReportDetailDO, Integer> {
    //今天是否已经举报被审核了10个，不为待审核的有多少个今天
    Integer countByUserIdAndStatusNotAndCreateTimeBetween(Integer userId, String status, Date startDate, Date endDate);

    Integer countByUserIdAndCreateTimeBetween(Integer userId, Date startDate, Date endDate);

    Integer countByUserIdAndReportId(Integer userId, ReportDO reportDO);

    Integer countByReportId(Integer reportId);

    List<ReportDetailDO> findAllByReportId(Integer reportId);
}
