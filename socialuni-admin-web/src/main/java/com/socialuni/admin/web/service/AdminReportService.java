package com.socialuni.admin.web.service;

import com.socialuni.admin.web.constant.AdminAuditResultType;
import com.socialuni.admin.web.model.ReportRO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.constant.ReportSourceType;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.sdk.constant.socialuni.ReportStatus;
import com.socialuni.social.sdk.constant.socialuni.SocialuniAuditContentType;
import com.socialuni.social.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.social.sdk.dao.DO.NotifyDO;
import com.socialuni.social.sdk.dao.DO.ReportDO;
import com.socialuni.social.sdk.dao.DO.ReportDetailDO;
import com.socialuni.social.sdk.dao.DO.SocialuniUnionIdDO;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkHasUnderageImgAuditDO;
import com.socialuni.social.sdk.dao.DO.keywords.KeywordsDO;
import com.socialuni.social.sdk.dao.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.social.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.social.sdk.dao.repository.*;
import com.socialuni.social.sdk.dao.repository.community.TalkAdultImgAuditRepository;
import com.socialuni.social.sdk.dao.utils.content.SocialuniContentDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.logic.factory.ReportFactory;
import com.socialuni.social.sdk.logic.service.KeywordsService;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.social.common.model.ResultRO;
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

    @Resource
    private TalkAdultImgAuditRepository talkAdultImgAuditRepository;

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
    public ResultRO<String> auditReportList(List<ReportRO> auditVOS) {
        ResultRO<String> resultRO = new ResultRO<>();
        for (ReportRO auditVO : auditVOS) {
            //首先校验 reportid是否存在
            ResultRO<String> methodResult = getStringResultVO(auditVO);
            if (methodResult != null) {
                return methodResult;
            }
        }
        resultRO.setData("审核成功");
        return resultRO;
    }


    @Transactional
    public ResultRO<String> getStringResultVO(ReportRO auditVO) {
        //区分类型
        //成年未成年的，则直接更改审核记录表，和修改动态的属性

        String auditType = auditVO.getViolateType();
        //图片类型的也支持直接审核
        String auditContentType = auditVO.getAuditContentType();

        Integer contentId = auditVO.getId();
        //如果仅仅是图片审核，则必然要修改图片审核的状态
        if (SocialuniAuditContentType.underageImg.equals(auditContentType)) {
            SocialuniTalkHasUnderageImgAuditDO socialuniTalkHasUnderageImgAuditDO = talkAdultImgAuditRepository.findOneById(contentId);
            //确认举报是否存在，只能处理未处理状态的。
            //判断是否违规
            if (socialuniTalkHasUnderageImgAuditDO == null) {
                throw new SocialParamsException("不存在的图片审核内容");
            }
            socialuniTalkHasUnderageImgAuditDO.setStatus(ContentStatus.enable);
            talkAdultImgAuditRepository.save(socialuniTalkHasUnderageImgAuditDO);
            contentId = socialuniTalkHasUnderageImgAuditDO.getTalkId();

            //仅审核为未成年和成年 ，则只更改图片审核状态和动态状态，就返回了，
            if (AdminAuditResultType.underageShowReportTypes.contains(auditType)) {
                SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(socialuniTalkHasUnderageImgAuditDO.getTalkId());
                //如果为成年，则修改动态状态
                if (AdminAuditResultType.adult.equals(auditType)) {
                    talkDO.setPeopleImgIsAdult(true);
                    SocialuniTalkDOUtil.save(talkDO);
                }
                return null;
            }
        }
        ReportDO reportDO;
        String violateType = auditVO.getViolateType();
        if (AdminAuditResultType.adult.equals(violateType)){
            throw new SocialParamsException("成年类型不能审核为违规");
        }
        //图片类型的也支持直接审核, 图片审核类型的需要模拟一个report
        if (SocialuniAuditContentType.underageImg.equals(auditContentType)) {
            Optional<ReportDO> reportDOOptional = reportRepository.findById(contentId);
            if (!reportDOOptional.isPresent()){
                SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(contentId);
                SocialuniUnionIdDO uniContentUnionIdDO = SocialuniUnionIdUtil.getUnionDOByUnionIdNotNull(contentId);
                reportDO = ReportFactory.createReportDO(ReportSourceType.systemAutoCheck, talkDO, uniContentUnionIdDO);
            }else {
                reportDO = reportDOOptional.get();
            }
            reportDO.setReportNum(reportDO.getReportNum() + 1);
            reportDO.setUpdateTime(new Date());
            //保存数据
            reportDO = reportRepository.save(reportDO);
        } else {
            //其他类型
            Optional<ReportDO> reportDOOptional = reportRepository.findById(contentId);
            //确认举报是否存在，只能处理未处理状态的。

            //判断是否违规
            if (!reportDOOptional.isPresent()) {
                throw new SocialParamsException("不存在的举报内容");
            }
            reportDO = reportDOOptional.get();
        }
        Integer systemUserId = SocialuniSystemConst.getSystemUserId();
        List<NotifyDO> notifyDOS = new ArrayList<>();
        //为待审核才继续处理
        if (ReportStatus.auditStatus.contains(reportDO.getStatus())) {
            //校验违规类型
            if (!SocialuniContentType.reportContentTypeTypes.contains(reportDO.getContentType())) {
                return new ResultRO<>("错误的违规内容类型");
            }

            //校验原因
            String auditNote = auditVO.getAuditNote();
            //不为不违规才为违规
            boolean violation = !AdminAuditResultType.noViolation.equals(violateType);
            if (StringUtils.isEmpty(auditNote) && !violation) {
                auditNote = "不违规";
//                    return new ResultVO<>("审核不违规，必须填写原因");
            }
            SocialUnionContentBaseDO modelDO = SocialuniContentDOUtil.getContentDOByContentId(reportDO.getContentId());
            //如果是违规
            if (violation) {
                if (StringUtils.isEmpty(violateType)) {
                    return new ResultRO<>("审核违规，必须选择违规类型");
                }
                if (!AdminAuditResultType.adminFrontHasUnderageShowReportTypes.contains(violateType)) {
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
        return null;
    }


}
