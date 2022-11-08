package com.socialuni.admin.web.service;

import com.socialuni.admin.web.constant.AdminAuditResultType;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.report.sdk.enumeration.ReportStatus;
import com.socialuni.social.report.sdk.repository.ReportRepository;
import com.socialuni.social.user.sdk.constant.UserType;
import com.socialuni.social.sdk.constant.ViolateLevel;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.user.sdk.constant.SocialuniUserStatus;
import com.socialuni.social.sdk.dao.store.ReportStore;
import com.socialuni.social.sdk.dao.utils.content.SocialuniContentDOUtil;
import com.socialuni.social.sdk.logic.entity.user.SocialUserViolationEntity;
import com.socialuni.social.sdk.logic.service.ReportService;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.model.DO.SocialUserViolationDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
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
    private ReportRepository reportApi;
    @Resource
    private ReportService reportService;
    @Resource
    private SocialuniUserRepository userApi;
    @Resource
    private ReportStore reportStore;
    @Resource
    private SocialUserViolationEntity socialUserViolationEntity;

    public void noViolateService(SocialuniUnionContentBaseDO modelDO, String auditNote, ReportDO ReportDO) {
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

        SocialuniContentDOUtil.save(modelDO);

        //user改为正常
        SocialuniUserDo violationUser = SocialuniUserUtil.getUserNotNull(modelDO.getUserId());
        String userStatus = violationUser.getStatus();
        //存在用户发表其他内容，被封的情况
        if (ReportStatus.auditStatus.contains(userStatus)) {
            violationUser.setStatus(SocialuniUserStatus.enable);
        } else {
            log.info("用户状态已经被更改：{}", userStatus);
        }
        userApi.savePut(violationUser);

        //更改report违规类型
        ReportDO.setAuditType(AdminAuditResultType.noViolation);
        ReportDO.setAuditNote(auditNote);
        //审核状态变更
        ReportDO.setStatus(ReportStatus.enable);
        ReportDO.setUpdateTime(curDate);
        ReportDO.setValid(false);
        reportApi.savePut(ReportDO);
        //发放奖励和修改举报详情内容
        reportService.reportPass(ReportDO, false);
    }

    public void violateService(SocialuniUnionContentBaseDO modelDO, String violateType, String auditNote, ReportDO ReportDO) {
        Date curDate = new Date();
        SocialuniUserDo violationUser = modelContentViolation(modelDO, violateType);

        //修改举报内容
        ReportDO.setAuditType(violateType);
        ReportDO.setStatus(ReportStatus.violation);
        ReportDO.setAuditNote(auditNote);
        ReportDO.setUpdateTime(curDate);
        ReportDO.setValid(true);
        reportApi.savePut(ReportDO);


        //不是轻微违规，则将所有待审核内容改为违规
        if (!AdminAuditResultType.slightViolation.equals(violateType)) {
            //查询用户所有为待审核和预审核的内容，改为违规
            List<?  extends ReportDO> ReportDOs = reportStore.queryUserOtherWaitAuditContent(violationUser.getUnionId());
            for (ReportDO linkReport : ReportDOs) {
                //修改关联内容的状态，为违规
                SocialuniUnionContentBaseDO linkModelDO = SocialuniContentDOUtil.getContentDOByContentId(linkReport.getContentId());
                //修改内容，需要修改状态、删除原因、更新时间
                linkModelDO.setStatus(ContentStatus.violation);
                //如果封禁的话，要改一下删除原因，删除原因，违规原因
                linkModelDO.setDeleteReason(violateType);
                linkModelDO.setUpdateTime(curDate);
                //内容违规则修改内容状态
                SocialuniContentDOUtil.save(linkModelDO);
                //修改举报内容
                linkReport.setAuditType(AdminAuditResultType.otherIllegalLinkage);
                linkReport.setStatus(ReportStatus.violation);
                linkReport.setAuditNote("关联其他举报内容违规id：" + ReportDO.getId());
                linkReport.setUpdateTime(curDate);
                linkReport.setValid(true);
            }
            reportApi.savePutAll(ReportDOs);
        }

        //如果有举报记录
        //发放奖励和修改举报详情内容
        reportService.reportPass(ReportDO, true);
    }

    public SocialuniUserDo modelContentViolation(SocialuniUnionContentBaseDO modelDO, String violateType) {
        Date curDate = new Date();
        //修改内容，需要修改状态、删除原因、更新时间
        modelDO.setStatus(ContentStatus.violation);
        //如果封禁的话，要改一下删除原因，删除原因，违规原因
        modelDO.setDeleteReason(violateType);
        modelDO.setUpdateTime(curDate);
        //内容违规则修改内容状态
        SocialuniContentDOUtil.save(modelDO);
        //处理举报
        //封禁用户
        //如果已经是违规，不需要改为删除
        SocialuniUserDo violationUser = SocialuniUserUtil.getUserNotNull(modelDO.getUserId());
        String vioReason = modelDO.getDeleteReason() + ",";
        //不为官方系统用户才可封禁
        if (!UserType.system.equals(violationUser.getType())) {
            userViolationHandler(violationUser, vioReason, curDate, violateType);
            userApi.savePut(violationUser);
            log.info("用户昵称和id为：" + violationUser.getNickname() + ":" + violationUser.getUnionId() + "用户状态改为：" + violationUser.getStatus());
        }
        return violationUser;
    }

    //更改user状态
    public void userViolationHandler(SocialuniUserDo violationUser, String vioReason, Date curDate, String violateType) {
        String vioLevel;
        //轻微
        if (AdminAuditResultType.slightViolation.equals(violateType)) {
            vioLevel = ViolateLevel.slight;
        } else if (AdminAuditResultType.generalViolationList.contains(violateType)) {
            //一般违规
            vioLevel = ViolateLevel.general;
        } else {
            //严重违规
            vioLevel = ViolateLevel.severely;
        }

        int violationDay = 0;
        SocialUserViolationDo SocialUserViolationDo = socialUserViolationEntity.getOrCreateViolationDO(violationUser.getUnionId());
        //轻微违规只删除内容
        if (ViolateLevel.slight.equals(vioLevel)) {
            vioReason += "删除违规内容";
        } else {
            //区分轻微、一般违规，和严重违规，一般和严重才增加次数
            Integer vioCount = SocialUserViolationDo.getViolationCount();
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
            SocialUserViolationDo.setViolationCount(vioCount + 1);
        }
        //所有违规通用
        SocialUserViolationDo.setViolationReason(vioReason);
        violationUser.setUpdateTime(curDate);

        //用户状态不为已封禁，才修改用户状态
        if (!violationUser.getStatus().equals(SocialuniUserStatus.violation)) {
            //如果封禁天数大于0，则封禁
            if (violationDay > 0) {
                //封禁
                violationUser.setStatus(SocialuniUserStatus.violation);
                //封禁日期不叠加，按最后得算
                //封禁截止日期
                SocialUserViolationDo.setViolationStartTime(new Date());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, violationDay);
                SocialUserViolationDo.setViolationEndTime(calendar.getTime());
            } else {
                violationUser.setStatus(SocialuniUserStatus.enable);
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
