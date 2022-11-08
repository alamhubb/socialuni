/*
package com.socialuni.admin.web.controller.oldadmin;

import com.qingchi.admin.modelvo.ReportVO;
import com.socialuni.social.model.common.ResultRO;
import com.qingchi.base.constant.status.ReportStatus;
import com.qingchi.base.repository.keywords.KeywordsRepository;
import com.qingchi.base.repository.notify.NotifyRepository;
import com.qingchi.base.service.NotifyService;
import com.qingchi.base.model.report.ReportDO;
import com.qingchi.base.repository.report.ReportRepository;
import com.qingchi.base.repository.talk.TalkRepository;
import com.qingchi.base.service.ViolationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author qinkaiyuan
 * @date 2020-03-15 22:05
 *//*

@RestController
@RequestMapping("report")
public class ReportHistoryController {
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private ViolationService violationService;
    @Resource
    private NotifyRepository notifyRepository;
    @Resource
    private NotifyService notifyService;
    @Resource
    private KeywordsRepository keywordsRepository;

    @Resource
    private TalkRepository talkRepository;

  */
/*  @PostMapping("queryReportHistory")
    public ResultVO<List<ReportVO>> queryReportHistory() {
        Pageable pageable = PageRequest.of(0, 5);
        //查询所有被举报的用户的，talk，并且按照举报次数和更新时间排序，并且talk状态为enable的
        Page<ReportDO> reportDOPage = reportRepository.findByStatusAndAuditTypeAndReceiveUserStatusNotOrderByIdDesc(pageable, CommonStatus.violation, ViolateType.pornInfo, CommonStatus.violation);
        List<ReportVO> reportVOS = reportDOPage.getContent().stream().map(ReportVO::new).collect(Collectors.toList());
//        List<SocialTalkDO> list = talkRepository.findTop10ByStatusOrderByReportNum(CommonStatus.enable);
//        return new ResultVO<>(list.stream().map(ReportTalkVO::new).collect(Collectors.toList()));
        return new ResultVO<>(reportVOS);
    }*//*


    @PostMapping("queryViolationHistory")
    public ResultRO<List<ReportVO>> queryViolationHistory() {
        Pageable pageable = PageRequest.of(0, 10);
        //查询所有被举报的用户的，talk，并且按照举报次数和更新时间排序，并且talk状态为enable的
        Page<ReportDO> reportDOPage = reportRepository.findByStatusNotInAndHasReviewFalseOrderByIdDesc(pageable, ReportStatus.auditStatus);
        List<ReportDO> queryDos = reportDOPage.getContent();
        List<ReportVO> reportVOS = queryDos.stream().map(ReportVO::new).collect(Collectors.toList());
//        List<SocialTalkDO> list = talkRepository.findTop10ByStatusOrderByReportNum(CommonStatus.enable);
//        return new ResultVO<>(list.stream().map(ReportTalkVO::new).collect(Collectors.toList()));


        List<ReportVO> result = new ArrayList<>();
        */
/*List<ReportVO> reviewList = new ArrayList<>();

        List<KeywordsDO> wordDOs = keywordsRepository.findAllByStatus(BaseStatus.enable);

        for (ReportVO reportVO : reportVOS) {
            if (!reportVO.getHasKeyword()) {
                result.add(reportVO);
            } else {
                reviewList.add(reportVO);
            }
        }*//*


        */
/*List<ReportDO> reviewDOs = new ArrayList<>();
        for (ReportDO reviewDO : queryDos) {
            for (ReportVO reportVO : reviewList) {
                if (reviewDO.getId().equals(reportVO.getId())) {
                    reviewDO.setHasReview(true);
                    reviewDOs.add(reviewDO);
                }
            }
        }
        reportRepository.saveAll(reviewDOs);
*//*
        //加一个发布之前的预审核阶段，
        //显示用户违规次数，触发违规关键词数量，
        //显示内容，用户，还是可以批量审核，
        // report状态改为预审核，违规或者不违规，
        // 动态改为预审核，

        //查询所有没有预审核结果的。执行预审核，触发预审核的id，关键字
        //预计审核触发的关键词

        //先查询出来最近的涉污涉黄的违规信息，然后看他们是否触发了关键词。

        //3个阶段，预审核、审核、校验审核
        //校验审核界面，
        //违规内容，用户，是否触发关键词，触发关键词数量，分别是哪几个，违规类型，

        //记录这个关键词，触发次数，查询出来的时候，知道绑定了哪个关键字，然后审核时，给他违规或者不违规加次数，然后加总次数。就能知道违规和不违规比例。
        //再记录下不违规时，他的上下文是什么

        return new ResultRO<>(reportVOS);
    }
}
*/
