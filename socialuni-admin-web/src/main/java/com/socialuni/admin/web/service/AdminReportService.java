package com.socialuni.admin.web.service;

import com.socialuni.admin.web.model.ReportVO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.constant.ReportStatus;
import com.socialuni.social.entity.model.DO.NotifyDO;
import com.socialuni.social.entity.model.DO.ReportDO;
import com.socialuni.social.entity.model.DO.ReportDetailDO;
import com.socialuni.social.entity.model.DO.base.BaseModelDO;
import com.socialuni.social.entity.model.DO.keywords.KeywordsDO;
import com.socialuni.social.entity.model.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.constant.ReportSourceType;
import com.socialuni.center.web.constant.ViolateType;
import com.socialuni.center.web.repository.*;
import com.socialuni.center.web.service.BaseModelUtils;
import com.socialuni.center.web.service.KeywordsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminReportService {
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



    /* *
     * 举报成功
     * * 举报正确类别，奖励10，举报错误类别奖励5，举报失败扣20，
     * *
     * * 审核过后，发放奖励
     * 这点应该在新增举报那个页面能控制，下面这俩
     * //正义值小于0，则每天只能举报2个，举报提示
     * //正义值小于300不能再举报
     * //给用户通知，您举报成功失败，奖励或扣除积分，每天满多少，低于0 2练个，低于200不能再举报
     * * 举报正确类别，奖励10，举报错误类别奖励5，举报失败扣20，
     * *
     * * 审核过后，发放奖励
     * *
     * * 如果已经被举报多少次不能再举报，举报超过多少先删除，talk状态要加一个审核中，2个人举报就被删，进入审核中，查询在审核中的和正常的
     * *
     * * 2个人举报就被删，进入审核中，查询在审核中的和正常的
     * * 举报审核通过的要加一个不违规标识
     * * 审核完就变成正常和违规，发放奖励
     * *
     * * 每个人每天只能举报10个，分值低于0的，每天只能举报两个，分值低于-200，不能再进行举报
     *
     * @return*/


    @Transactional
    public ResultRO<String> getStringResultVO(ReportVO auditVO) {
        Optional<ReportDO> reportDOOptional = reportRepository.findById(auditVO.getId());
        //确认举报是否存在，只能处理未处理状态的。

        //判断是否违规
        if (reportDOOptional.isPresent()) {
            List<NotifyDO> notifyDOS = new ArrayList<>();
            Integer systemUserId = SocialAppConfig.getSystemUserId();
            ReportDO reportDO = reportDOOptional.get();
            //为待审核才继续处理
            if (ReportStatus.auditStatus.contains(reportDO.getStatus())) {
                //校验违规类型
                if (!ContentType.reportContentTypeTypes.contains(reportDO.getReportContentType())) {
                    return new ResultRO<>("错误的违规内容类型");
                }

                //校验原因
                String auditNote = auditVO.getAuditNote();
                //不为不违规才为违规
                boolean violation = !ViolateType.noViolation.equals(auditVO.getViolateType());
                if (StringUtils.isEmpty(auditNote) && !violation) {
                    auditNote = "不违规";
//                    return new ResultVO<>("审核不违规，必须填写原因");
                }

                BaseModelDO modelDO = BaseModelUtils.getModelByReport(reportDO);
                //如果是违规
                if (violation) {
                    String violateType = auditVO.getViolateType();
                    if (StringUtils.isEmpty(violateType)) {
                        return new ResultRO<>("审核违规，必须选择违规类型");
                    }
                    if (!ViolateType.adminFrontShowReportTypes.contains(violateType)) {
                        return new ResultRO<>("错误的违规类型");
                    }
                    violationService.violateService(modelDO, violateType, auditNote, reportDO);
                    //给用户发送被封通知,推送消息
//                    NotifyDO notifyDO = notifyRepository.save(new NotifyDO(systemUser.getId(), modelDO.getUserId(), reportDO.getId(), NotifyType.violation));
//                    notifyDOS.add(notifyDO);
                } else {
                    violationService.noViolateService(modelDO, auditNote, reportDO);
                }

                //不为系统审查才给举报用户发消息
                if (ReportSourceType.userReport.equals(reportDO.getReportSourceType())) {

                    List<ReportDetailDO> reportDetailDOS = reportDetailRepository.findAllByReportId(reportDO.getId());

                    for (ReportDetailDO reportDetailDO : reportDetailDOS) {
//                        NotifyDO notifyDO = new NotifyDO(systemUser.getId(), reportDetailDO.getUserId(), reportDO.getId(), NotifyType.report_result);
//                        notifyDOS.add(notifyDO);
                    }
                }

                //添加是否触发了关键词，触发了关键词就修改关键词次数重新计算违规率
                List<KeywordsTriggerDetailDO> keywordsTriggers = keywordsTriggerDetailRepository.findAllByReportId(reportDO.getId());
                for (KeywordsTriggerDetailDO keywordsTrigger : keywordsTriggers) {
                    //审核结果状态
                    String auditResult = reportDO.getStatus();

                    keywordsTrigger.setAuditResult(auditResult);
                    keywordsTrigger.setUpdateTime(new Date());
                    KeywordsDO wordDO = keywordsRepository.findById(keywordsTrigger.getKeywordsId()).get();
                    keywordsService.calculateViolateRatioByReportStatus(auditResult, keywordsTrigger, wordDO);
                }

                notifyRepository.saveAll(notifyDOS);
//                notifyService.sendNotifies(notifyDOS, systemUser);
            }
        }
        return null;
    }


}
