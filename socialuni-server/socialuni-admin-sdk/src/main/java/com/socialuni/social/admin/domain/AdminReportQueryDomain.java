package com.socialuni.social.admin.domain;

import com.socialuni.social.admin.facede.AdminDevAccountFacade;
import com.socialuni.social.admin.model.ReportRO;
import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.report.sdk.enumeration.ReportStatus;
import com.socialuni.social.report.sdk.dao.repository.ReportRepository;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkHasUnderageImgAuditDO;
import com.socialuni.social.sdk.dao.repository.community.TalkAdultImgAuditRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminReportQueryDomain {
    @Resource
    private ReportRepository reportApi;
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
        DevAccountModel user = AdminDevAccountFacade.getAdminDevAccountNotNull();
        List<? extends ReportDO> ReportDOs;
        if (AdminDevAccountFacade.isCenter()) {
            ReportDOs = reportApi.findTop20ByStatusInOrderByCreateTimeAsc(ReportStatus.auditStatus);
        } else {
            //用户自己删除了也没用，只要触发举报都会审核。
            ReportDOs = reportApi.findTop20ByStatusInAndDevIdOrderByCreateTimeAsc(ReportStatus.auditStatus, user.getId());
        }
        List<ReportRO> reportVOS = ReportDOs.stream().map(ReportRO::new).collect(Collectors.toList());

        List<SocialuniTalkHasUnderageImgAuditDO> list = talkAdultImgAuditRepository.findTop20ByStatusAndDevIdOrderByIdAsc(ContentStatus.preAudit, DevAccountFacade.getDevIdNotNull());

        List<ReportRO> reportVOS1 = list.stream().map(ReportRO::new).collect(Collectors.toList());

        reportVOS.addAll(reportVOS1);

        return reportVOS;

    }


}
