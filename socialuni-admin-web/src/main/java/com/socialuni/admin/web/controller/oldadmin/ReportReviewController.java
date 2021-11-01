/*
package com.socialuni.admin.web.controller.oldadmin;

import com.qingchi.admin.modelvo.ReportVO;
import com.socialuni.social.model.common.ResultRO;
import com.qingchi.base.repository.notify.NotifyRepository;
import com.qingchi.base.service.NotifyService;
import com.qingchi.base.model.report.ReportDO;
import com.qingchi.base.repository.report.ReportRepository;
import com.qingchi.base.repository.talk.TalkRepository;
import com.qingchi.base.service.ViolationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

*
 * @author qinkaiyuan
 * @date 2020-03-15 22:05


@RestController
@RequestMapping("report")
public class ReportReviewController {
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private ViolationService violationService;
    @Resource
    private NotifyRepository notifyRepository;
    @Resource
    private NotifyService notifyService;

    @Resource
    private TalkRepository talkRepository;

    @PostMapping("reviewReports")
    public ResultRO<Object> reviewReports(@RequestBody List<ReportVO> auditVOS) {
        List<ReportDO> list = new ArrayList<>();
        for (ReportVO auditVO : auditVOS) {
            Optional<ReportDO> optionalReportDO = reportRepository.findById(auditVO.getId());
            if (optionalReportDO.isPresent()) {
                ReportDO reportDO = optionalReportDO.get();
                reportDO.setHasReview(true);
                list.add(reportDO);
            }
        }
        reportRepository.saveAll(list);
        return new ResultRO<>();
    }

}
*/
