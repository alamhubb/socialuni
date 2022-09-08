package com.socialuni.admin.web.service;

import com.socialuni.sdk.dao.repository.*;

import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.ReportStatus;
import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.user.SocialUserViolationDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.constant.ViolateLevel;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.logic.domain.BaseModelService;
import com.socialuni.sdk.logic.entity.user.SocialUserViolationEntity;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.logic.service.BaseModelUtils;
import com.socialuni.sdk.logic.service.KeywordsService;
import com.socialuni.sdk.logic.service.ReportService;
import com.socialuni.sdk.dao.store.ReportStore;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ViolationService {
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private UserImgRepository userImgRepository;
    @Resource
    private MessageRepository messageRepository;
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private ReportService reportService;
    @Resource
    private UserRepository userRepository;
    @Resource
    private KeywordsService keywordsService;
    @Resource
    private BaseModelService baseModelService;
    @Resource
    private ReportStore reportStore;
    @Resource
    private SocialUserViolationEntity socialUserViolationEntity;

    public void noViolateService(BaseModelDO modelDO, String auditNote, ReportDO reportDO) {
        Date curDate = new Date();
        //审核通过不再接受举报，前台点击举报时，提示已官方审核通过
        //talk状态变更
        String contentStatus = modelDO.getStatus();
        //存在被举报后，用户自己把这条状态删了的情况
        if (ReportStatus.auditStatus.contains(contentStatus)) {
            modelDO.setUpdateTime(curDate);
            modelDO.setStatus(ContentStatus.enable);
        } else {
            log.info("用户已经自行删除此条内容：{}", contentStatus);
        }

        baseModelService.save(modelDO);

        //user改为正常
        SocialuniUserDO violationUser = SocialuniUserUtil.getUserNotNull(modelDO.getUserId());
        String userStatus = violationUser.getStatus();
        //存在用户发表其他内容，被封的情况
        if (ReportStatus.auditStatus.contains(userStatus)) {
            violationUser.setStatus(UserStatus.enable);
        } else {
            log.info("用户状态已经被更改：{}", userStatus);
        }
        userRepository.save(violationUser);

        //更改report违规类型
        reportDO.setAuditType(ViolateType.noViolation);
        reportDO.setAuditNote(auditNote);
        //审核状态变更
        reportDO.setStatus(ReportStatus.enable);
        reportDO.setUpdateTime(curDate);
        reportDO.setValid(false);
        reportRepository.save(reportDO);
        //发放奖励和修改举报详情内容
        reportService.reportPass(reportDO, false);
    }

    public void violateService(BaseModelDO modelDO, String violateType, String auditNote, ReportDO reportDO) {
        Date curDate = new Date();
        SocialuniUserDO violationUser = modelContentViolation(modelDO, violateType);

        //修改举报内容
        reportDO.setAuditType(violateType);
        reportDO.setStatus(ReportStatus.violation);
        reportDO.setAuditNote(auditNote);
        reportDO.setUpdateTime(curDate);
        reportDO.setValid(true);
        reportRepository.save(reportDO);


        //不是轻微违规，则将所有待审核内容改为违规
        if (!ViolateType.slightViolation.equals(violateType)) {
            //查询用户所有为待审核和预审核的内容，改为违规
            List<ReportDO> reportDOS = reportStore.queryUserOtherWaitAuditContent(violationUser.getId());
            for (ReportDO linkReport : reportDOS) {
                //修改关联内容的状态，为违规
                BaseModelDO linkModelDO = BaseModelUtils.getModelByReport(linkReport);
                //修改内容，需要修改状态、删除原因、更新时间
                linkModelDO.setStatus(ContentStatus.violation);
                //如果封禁的话，要改一下删除原因，删除原因，违规原因
                linkModelDO.setDeleteReason(violateType);
                linkModelDO.setUpdateTime(curDate);
                //内容违规则修改内容状态
                baseModelService.save(linkModelDO);
                //修改举报内容
                linkReport.setAuditType(ViolateType.otherIllegalLinkage);
                linkReport.setStatus(ReportStatus.violation);
                linkReport.setAuditNote("关联其他举报内容违规id：" + reportDO.getId());
                linkReport.setUpdateTime(curDate);
                linkReport.setValid(true);
            }
            reportRepository.saveAll(reportDOS);
        }

        //如果有举报记录
        //发放奖励和修改举报详情内容
        reportService.reportPass(reportDO, true);
    }

    public SocialuniUserDO modelContentViolation(BaseModelDO modelDO, String violateType) {
        Date curDate = new Date();
        //修改内容，需要修改状态、删除原因、更新时间
        modelDO.setStatus(ContentStatus.violation);
        //如果封禁的话，要改一下删除原因，删除原因，违规原因
        modelDO.setDeleteReason(violateType);
        modelDO.setUpdateTime(curDate);
        //内容违规则修改内容状态
        baseModelService.save(modelDO);
        //处理举报
        //封禁用户
        //如果已经是违规，不需要改为删除
        SocialuniUserDO violationUser = SocialuniUserUtil.getUserNotNull(modelDO.getUserId());
        String vioReason = modelDO.getDeleteReason() + ",";
        //不为官方系统用户才可封禁
        if (!UserType.system.equals(violationUser.getType())) {
            userViolationHandler(violationUser, vioReason, curDate, violateType);
            userRepository.save(violationUser);
            System.out.println("用户昵称和id为：" + violationUser.getNickname() + ":" + violationUser.getId() + "用户状态改为：" + violationUser.getStatus());
        }
        return violationUser;
    }

    //更改user状态
    public void userViolationHandler(SocialuniUserDO violationUser, String vioReason, Date curDate, String violateType) {
        String vioLevel;
        //轻微
        if (ViolateType.slightViolation.equals(violateType)) {
            vioLevel = ViolateLevel.slight;
            //一般违规
        } else if (ViolateType.generalViolationList.contains(violateType)) {
            vioLevel = ViolateLevel.general;
            //严重违规
        } else {
            vioLevel = ViolateLevel.severely;
        }

        int violationDay = 0;
        SocialUserViolationDO socialUserViolationDO = socialUserViolationEntity.getOrCreateViolationDO(violationUser.getId());
        //轻微违规只删除内容
        if (ViolateLevel.slight.equals(vioLevel)) {
            vioReason += "删除违规内容";
        } else {
            //区分轻微、一般违规，和严重违规，一般和严重才增加次数
            Integer vioCount = socialUserViolationDO.getViolationCount();
            //一般违规
            if (ViolateLevel.general.equals(vioLevel)) {
                //第一次不封禁,轻微违规
                if (vioCount < 1) {
                    vioReason += "账号警告";
                } else {
                    if (vioCount < 2) {
                        vioReason += "封禁1天";
                        violationDay = 1;
                    } else if (vioCount < 3) {
                        vioReason += "封禁3天";
                        violationDay = 3;
                    } else {
                        vioReason += "封禁7天";
                        violationDay = 7;
                    }
                }
            } else {
                if (vioCount < 2) {
                    vioReason += "封禁7天";
                    violationDay = 7;
                } else if (vioCount < 5) {
                    vioReason += "封禁1月";
                    violationDay = 30;
                } else {
                    vioReason += "封禁3月";
                    violationDay = 90;
                }
            }
            socialUserViolationDO.setViolationCount(vioCount + 1);
        }
        //所有违规通用
        socialUserViolationDO.setViolationReason(vioReason);
        violationUser.setUpdateTime(curDate);

        //用户状态不为已封禁，才修改用户状态
        if (!violationUser.getStatus().equals(UserStatus.violation)) {
            //如果封禁天数大于0，则封禁
            if (violationDay > 0) {
                //封禁
                violationUser.setStatus(UserStatus.violation);
                //封禁日期不叠加，按最后得算
                //封禁截止日期
                socialUserViolationDO.setViolationStartTime(new Date());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, violationDay);
                socialUserViolationDO.setViolationEndTime(calendar.getTime());
            } else {
                violationUser.setStatus(UserStatus.enable);
            }
        }
        //不删除之前的内容
        //更改时间时，需要判断目前的 违规截止时间，如果大于当前日期，则加日期，否则已现在开始日期计算。
        //删除此用户的所有动态
//        talkRepository.updateUserTalkStatusIn(violationUser.getId(), CommonStatus.contentEnableStatus);
        //删除此用户的所有评论
//        commentRepository.updateUserCommentStatusIn(violationUser.getId(), CommonStatus.contentEnableStatus);
    }
}
