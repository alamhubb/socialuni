package com.socialuni.admin.web.domain;

import com.socialuni.admin.web.model.ReportRO;
import com.socialuni.admin.web.service.ViolationService;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.sdk.constant.socialuni.ReportStatus;
import com.socialuni.social.sdk.dao.DO.ReportDO;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkHasUnderageImgAuditDO;
import com.socialuni.social.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.social.sdk.dao.repository.*;
import com.socialuni.social.sdk.dao.repository.community.TalkAdultImgAuditRepository;
import com.socialuni.social.sdk.logic.service.KeywordsService;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminReportQueryDomain {
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private ViolationService violationService;
    @Resource
    private NotifyRepository notifyRepository;
    @Resource
    private KeywordsService keywordsService;
    //    @Resource
//    private NotifyService notifyService;
    @Resource
    private ReportDetailRepository reportDetailRepository;
    @Resource
    private KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;
    @Resource
    private KeywordsRepository keywordsRepository;
    @Resource
    private TalkAdultImgAuditRepository talkAdultImgAuditRepository;


    public List<ReportRO> queryWaitAuditReports() {
        //表里加标识，还是维护一张表，有什么利弊
        // 好处 目前可以实现，同一逻辑
        // 坏处
        // 好处就一个表
        // 坏处不知道怎么写
        // 表里存一个租户id

        //联盟里的所有用户，相当于有个苹果id，有个联盟id， 各个渠道对应一个联盟id，
        //如果用户只有一个渠道


        //被举报的内容和待审核的成年照片一起审核
        //待审核的成年照片，打个标识？ is成年照片审核。
        //查询所有被举报的用户的，talk，并且按照举报次数和更新时间排序，并且talk状态为enable的
        DevAccountDO user = DevAccountUtils.getAdminDevAccountNotNull();
        List<ReportDO> reportDOS;
        if (DevAccountUtils.isCenter()) {
            reportDOS = reportRepository.findTop20ByStatusInOrderByCreateTimeAsc(ReportStatus.auditStatus);
        } else {
            //用户自己删除了也没用，只要触发举报都会审核。
            reportDOS = reportRepository.findTop20ByStatusInAndDevIdOrderByCreateTimeAsc(ReportStatus.auditStatus, user.getId());
        }
        List<ReportRO> reportVOS = reportDOS.stream().map(ReportRO::new).collect(Collectors.toList());

        List<SocialuniTalkHasUnderageImgAuditDO> list = talkAdultImgAuditRepository.findTop20ByStatusOrderByIdAsc(ContentStatus.preAudit);

        List<ReportRO> reportVOS1 = list.stream().map(ReportRO::new).collect(Collectors.toList());

        reportVOS.addAll(reportVOS1);

        return reportVOS;

    }


}
