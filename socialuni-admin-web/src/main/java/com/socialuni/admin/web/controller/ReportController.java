package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.constant.AdminAuditResultType;
import com.socialuni.admin.web.domain.AdminReportQueryDomain;
import com.socialuni.admin.web.model.ReportRO;
import com.socialuni.admin.web.service.AdminReportService;
import com.socialuni.admin.web.service.AdminUserService;
import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.community.sdk.entity.SocialuniCommentDO;
import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;
import com.socialuni.social.community.sdk.repository.CommentRepository;
import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.report.sdk.repository.ReportRepository;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.dao.repository.KeywordsRepository;
import com.socialuni.social.sdk.dao.repository.NotifyRepository;
import com.socialuni.social.sdk.dao.repository.dev.ThirdUserRepository;
import com.socialuni.social.sdk.dao.store.TalkQueryStore;
import com.socialuni.social.sdk.logic.service.KeywordsService;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("report")
public class ReportController {
    @Resource
    private ReportRepository reportApi;
    @Resource
    private AdminReportService adminReportService;
    @Resource
    private AdminReportQueryDomain adminReportQueryDomain;

    //被举报的内容和待审核的成年照片一起审核
    //待审核的成年照片，打个标识？ is成年照片审核。


/*

    @RequestMapping("getViolation")
    public ResultRO<Object> getViolation() {
        List<SocialTalkDO> talkDOS = talkRepository.findTop2000ByStatusAndViolateTypeOrderByIdDesc(ContentStatus.violation, ViolateType.pornInfo);
        VioKeywordsUtil.gatherKeywords(talkDOS);
        return new ResultRO<>();
    }
*/

    @RequestMapping("queryReportTypes")
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(AdminAuditResultType.adminFrontHasUnderageShowReportTypes);
    }

    @PostMapping("queryReports")
    public ResultRO<List<ReportRO>> queryReports() {
        List<ReportRO> reportVOS = adminReportQueryDomain.queryWaitAuditReports();
        return new ResultRO<>(reportVOS);
    }


    @PostMapping("reportAuditList")
    public ResultRO<String> reportAuditList(@RequestBody @NotNull List<ReportRO> auditVOS) {
        return adminReportService.auditReportList(auditVOS);
    }

    @PostMapping("reportAudit")
    public ResultRO<String> reportAudit(@RequestBody @Valid @NotNull ReportRO auditVO) {
        //首先校验 reportid是否存在
        ResultRO<String> resultRO = new ResultRO<>();
        ResultRO<String> methodResult = adminReportService.getStringResultVO(auditVO);
        if (methodResult != null) return methodResult;
        resultRO.setData("审核成功");
        return resultRO;
    }

    @PostMapping("queryUserReports")
    public ResultRO<List<ReportRO>> queryReports(Integer userId) {
        //展示用户的前多少条动态，评论，

        /*SocialuniUserDo user = new SocialuniUserDo();
        user.setId(userId);*/
        //查询所有被举报的用户的，talk，并且按照举报次数和更新时间排序，并且talk状态为enable的
//        List<ReportDO> reportDOS = reportRepository.findTop10ByReceiveUserAndStatusOrderByUpdateTimeDesc(user, CommonStatus.violation);
        List<?  extends ReportDO> ReportDOs = new ArrayList<>();
        List<ReportRO> reportVOS = ReportDOs.stream().map(ReportRO::new).collect(Collectors.toList());
//        List<SocialTalkDO> list = talkRepository.findTop10ByStatusOrderByReportNum(CommonStatus.enable);
//        return new ResultVO<>(list.stream().map(ReportTalkVO::new).collect(Collectors.toList()));
        return new ResultRO<>(reportVOS);
    }

    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    ThirdUserRepository thirdUserRepository;
    @Resource
    TalkQueryStore talkQueryStore;
    @Resource
    CommentRepository commentApi;
    @Resource
    AdminUserService adminUserService;


    @PostMapping("queryUserContentsByPhoneNum")
    public ResultRO<List<ReportRO>> queryUserContentsByPhoneNum(String phoneNum) {
        SocialuniUserDo user = adminUserService.getUserByPhoneNum(phoneNum);

        //查询用户10条被举报的内容
        List<?  extends ReportDO> ReportDOs = reportApi.findTop10ByContentUserIdOrderByCreateTimeDesc(user.getUnionId());
        List<ReportRO> reportVOS = ReportDOs.stream().map(ReportRO::new).collect(Collectors.toList());

        //查询用户10条动态
        List<?  extends SocialuniTalkDO>  talkDOS = talkQueryStore.queryTalksTop10ByUser(new ArrayList<>(), user.getUnionId());
        List<ReportRO> talkReportVOS = talkDOS.stream().map(ReportRO::new).collect(Collectors.toList());

        //查询用户10条评论
        List<?  extends SocialuniCommentDO> commentDOS = commentApi.findTop10ByUserIdOrderByUpdateTimeDesc(user.getUnionId());
        List<ReportRO> commentReportVOS = commentDOS.stream().map(ReportRO::new).collect(Collectors.toList());

        reportVOS.addAll(talkReportVOS);
        reportVOS.addAll(commentReportVOS);
        return new ResultRO<>(reportVOS);
    }
    /*









     *//* 举报成功
  举报正确类别，奖励10，举报错误类别奖励5，举报失败扣20，
 
  审核过后，发放奖励
 这点应该在新增举报那个页面能控制，下面这俩
 //正义值小于0，则每天只能举报2个，举报提示
 //正义值小于300不能再举报
 //给用户通知，您举报成功失败，奖励或扣除积分，每天满多少，低于0 2练个，低于200不能再举报
  举报正确类别，奖励10，举报错误类别奖励5，举报失败扣20，
 
  审核过后，发放奖励
 
  如果已经被举报多少次不能再举报，举报超过多少先删除，talk状态要加一个审核中，2个人举报就被删，进入审核中，查询在审核中的和正常的
 
  2个人举报就被删，进入审核中，查询在审核中的和正常的
  举报审核通过的要加一个不违规标识
  审核完就变成正常和违规，发放奖励
 
  每个人每天只能举报10个，分值低于0的，每天只能举报两个，分值低于-200，不能再进行举报*//*

    public void modelIsViolation(ReportAuditVO auditVO) {
        String auditType = auditVO.getAuditType();
        if (StringUtils.isEmpty(auditType)) {
            return new ResultVO<>("审核违规，必须选择审核类型");
        }
        reportDO.setAuditType(auditType);
        if (reportContentType.equals(ReportContentType.talk)) {
            SocialTalkDO talkDO = reportDO.getTalk();
            if (ReportType.noViolationList.contains(auditType)) {
                violationService.generalViolationsReportHandler(talkDO, auditType, reportDO);
            } else {
                violationService.modelViolationHandler(talkDO, auditType, true);
            }
            //给用户发送被封通知,推送消息
            NotifyDO notifyDO = notifyRepository.save(new NotifyDO(systemUser, talkDO.getUser(), talkDO));
            notifyDOS.add(notifyDO);
        } else if (reportContentType.equals(ReportContentType.comment)) {
            SocialCommentDO commentDO = reportDO.getComment();
            if (ReportType.noViolationList.contains(auditType)) {
                violationService.generalViolationsReportHandler(commentDO, auditType, reportDO);
            } else {
                violationService.modelViolationHandler(commentDO, auditType, true);
            }
            //给用户发送被封通知,推送消息
            NotifyDO notifyDO = notifyRepository.save(new NotifyDO(systemUser, commentDO.getUser(), commentDO, NotifyType.delete_comment));
            notifyDOS.add(notifyDO);
        } else if (reportContentType.equals(ReportContentType.userImg)) {
            UserImgDO userImgDO = reportDO.getUserImg();
            if (ReportType.noViolationList.contains(auditType)) {
                violationService.generalViolationsReportHandler(userImgDO, auditType, reportDO);
            } else {
                violationService.modelViolationHandler(userImgDO, auditType, true);
            }
            //给用户发送被封通知,推送消息
            NotifyDO notifyDO = notifyRepository.save(new NotifyDO(systemUser, userImgDO.getUser(), NotifyType.report_user_img));
            notifyDOS.add(notifyDO);
        } else if (reportContentType.equals(ReportContentType.message)) {
            MessageDO message = reportDO.getMessage();
            if (ReportType.noViolationList.contains(auditType)) {
                violationService.generalViolationsReportHandler(message, auditType, reportDO);
            } else {
                violationService.modelViolationHandler(message, auditType, true);
            }
            //给用户发送被封通知,推送消息
            NotifyDO notifyDO = notifyRepository.save(new NotifyDO(systemUser, message.getUser(), NotifyType.report_message));
            notifyDOS.add(notifyDO);
        } else {
            Logger.logger.error("错误的审核内容类型");
            return new ResultVO<>("错误的类型");
        }
    }

    public void modelIsNormal() {
        if (StringUtils.isEmpty(auditNote)) {
            return new ResultVO<>("审核不违规，必须填写原因");
        }
        reportDO.setAuditType(ReportType.noViolation);
        //审核状态变更
        reportDO.setStatus(CommonStatus.noViolation);
        reportDO.setUpdateTime(curDate);
        BaseModelDO modelDO = reportDO.getTalk();
        if (reportContentType.equals(ReportContentType.talk)) {
            modelDO = reportDO.getTalk();
        } else if (reportContentType.equals(ReportContentType.comment)) {
            modelDO = reportDO.getComment();
        } else if (reportContentType.equals(ReportContentType.userImg)) {
            modelDO = reportDO.getUserImg();
        } else if (reportContentType.equals(ReportContentType.message)) {
            modelDO = reportDO.getMessage();
        } else {
            Logger.logger.error("错误的审核内容类型");
            return new ResultVO<>("错误的类型");
        }
        //user改为正常
        SocialUserDO receiveUser = modelDO.getUser();
        String userStatus = receiveUser.getStatus();
        if (userStatus.equals(CommonStatus.audit)) {
            receiveUser.setStatus(CommonStatus.enable);
        } else {
            Logger.logger.info("用户状态已经被更改：{}", userStatus);
        }
        //审核通过不再接受举报，前台点击举报时，提示已官方审核通过
        //talk状态变更
        String contentStatus = modelDO.getStatus();
        if (contentStatus.equals(CommonStatus.audit)) {
            modelDO.setUpdateTime(curDate);
            modelDO.setStatus(CommonStatus.noViolation);
        } else {
            Logger.logger.info("用户已经自行删除此条内容：{}", contentStatus);
        }
        List<ReportDetailDO> reportDetailDOS = reportDO.getChildReports();
        //变更detail
        for (ReportDetailDO reportDetailDO : reportDetailDOS) {
            SocialUserDO detailUser = reportDetailDO.getUser();
            //如果今天已经成功举报了10个以上，则不再发放奖励
            reportDetailDO.setStatus(CommonStatus.noViolation);
            //错误的举报，user减分
            detailUser.setJusticeValue(detailUser.getJusticeValue() + AppConfigConst.reportErrorValue);
            JusticeValueOrderDO justiceValueOrderDO = new JusticeValueOrderDO();
            justiceValueOrderDO.setCreateTime(curDate);
            //默认不奖励
            justiceValueOrderDO.setUser(detailUser);
            justiceValueOrderDO.setReportDetail(reportDetailDO);
            justiceValueOrderDO.setJusticeValue(AppConfigConst.reportErrorValue);
            justiceValueOrderRepository.save(justiceValueOrderDO);
            //不允许重复举报
            //应该查询今天奖励了多少次了
            //如果类型一直加10分
            //
        }
    }

    public void sendReportNotifies() {
        String notifyType;
        if (reportContentType.equals(ReportContentType.talk)) {
            notifyType = NotifyType.report_talk;
        } else if (reportContentType.equals(ReportContentType.userImg)) {
            notifyType = NotifyType.report_user_img;
        } else if (reportContentType.equals(ReportContentType.message)) {
            notifyType = NotifyType.report_message;
        } else {
            notifyType = NotifyType.report_comment;
        }
        List<ReportDetailDO> reportDetailDOS = reportDO.getChildReports();
        for (ReportDetailDO reportDetailDO : reportDetailDOS) {
            NotifyDO notifyDO = new NotifyDO(systemUser, reportDetailDO.getUser(), reportDO, notifyType);
            notifyDOS.add(notifyDO);
        }
        notifyRepository.saveAll(notifyDOS);
        reportRepository.save(reportDO);
        notifyService.sendNotifies(notifyDOS);
    }*/

}
