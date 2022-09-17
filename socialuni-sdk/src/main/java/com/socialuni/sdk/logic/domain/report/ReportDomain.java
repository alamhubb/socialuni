package com.socialuni.sdk.logic.domain.report;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.*;
import com.socialuni.sdk.constant.config.AppConfigStatic;
import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import com.socialuni.sdk.dao.repository.*;
import com.socialuni.sdk.logic.domain.BaseModelService;
import com.socialuni.sdk.logic.factory.ReportFactory;
import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.DO.ReportDetailDO;
import com.socialuni.sdk.dao.DO.UniContentUnionIdDO;
import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.SocialuniReportAddQO;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkImgAddQO;

import com.socialuni.sdk.logic.service.KeywordsService;
import com.socialuni.sdk.logic.service.KeywordsTriggerService;
import com.socialuni.sdk.utils.*;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
public class ReportDomain {
    @Resource
    private ReportDetailRepository reportDetailRepository;
    @Resource
    private JusticeValueOrderRepository justiceValueOrderRepository;
    @Resource
    private KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private KeywordsTriggerService keywordsTriggerService;
    @Resource
    private UserRepository userRepository;

    @Resource
    private KeywordsRepository keywordsRepository;

    @Resource
    private KeywordsService keywordsService;

    @Resource
    private ReportFactory reportFactory;
    @Resource
    BaseModelService baseModelService;
    @Resource
    TalkRedis talkRedis;

    @Transactional
    public void checkImgCreateReport(List<SocialTalkImgAddQO> imgs) {
        if (imgs.size() > 0) {
            for (SocialTalkImgAddQO img : imgs) {
                String imgFullUrl = SocialuniSystemConst.getStaticResourceUrl() + img.getSrc();
                WxUtil.checkImgSecPost(imgFullUrl);
                QQUtil.checkImgSecPost(imgFullUrl);
            }
        }
    }

    //根据是否违规生成举报信息，只需要basedo基础信息就够
//    @Transactional
    @Async
    public BaseModelDO checkKeywordsCreateReport(BaseModelDO modelDO) {
        String contentType = modelDO.getContentType();

        //不为空才校验内容
        if (StringUtils.isNotEmpty(modelDO.getContent())) {
            //网易三方审查
//            AntispamDO antispamDO = WangYiUtil.checkWYContentSecPost(modelDO);

            // 校验是否触发关键词
            List<KeywordsTriggerDetailDO> keywordsTriggers = keywordsTriggerService
                    .checkContentTriggerKeywords(modelDO, contentType, AppConfigStatic.getKeywordDOs(), false);


//            if (!CollectionUtils.isEmpty(keywordsTriggers) || antispamDO.hasViolate()) {
            //如果触发了关键词
            if (!CollectionUtils.isEmpty(keywordsTriggers)) {
                String reportCause;
                ReportDO reportDO;
            /*    if (antispamDO.hasViolate()) {
                    reportCause = antispamDO.getCause();
                    reportDO = reportFactory.createReportDO(reportCause, modelDO, ReportSourceType.antispam, antispamDO.getId());
                } else {*/
                reportCause = "系统自动审查";

                UniContentUnionIdDO uniContentUnionIdDO = UnionIdUtil.getUnionDOByUnionIdNotNull(modelDO.getUnionId());
                reportDO = reportFactory.createReportDO(reportCause, uniContentUnionIdDO, modelDO, ReportSourceType.systemAutoCheck, uniContentUnionIdDO.getFromDevId());
//                }

                //这里之后才能校验
                // 设置model
                //可以放到 report的 store 中
                //保存数据
                reportDO = reportRepository.save(reportDO);
                //生成举报详情
                ReportDetailDO reportDetailDO =
                        new ReportDetailDO(reportCause, ViolateType.pornInfo, reportDO, uniContentUnionIdDO, modelDO);

                reportDetailRepository.save(reportDetailDO);


                Integer reportId = reportDO.getId();

                if (keywordsTriggers.size() > 0) {
                    //为触发记录关联 report
                    keywordsTriggers.forEach(keywordsTriggerDetailDO -> {
                        keywordsTriggerDetailDO.setReportId(reportId);
                    });

                    //保存触发记录
                    keywordsTriggerDetailRepository.saveAll(keywordsTriggers);
                }


                //更新 model
                modelDO.setStatus(ContentStatus.preAudit);
                modelDO.setUpdateTime(new Date());
                baseModelService.save(modelDO);
                if (contentType.equals(SocialuniContentType.talkImg)) {
                    //同步更新talk状态
                    SocialTalkImgDO socialTalkImgDO = (SocialTalkImgDO) modelDO;
                    Integer talkId = socialTalkImgDO.getTalkId();
                    SocialTalkDO talkDO = TalkUtils.getNotNull(talkId);

                    talkDO.setStatus(ContentStatus.preAudit);
                    talkDO.setUpdateTime(new Date());
                    baseModelService.save(modelDO);
                }
            }
        }
        return modelDO;
    }


    @Transactional
    public ResultRO<String> userReportContent(SocialuniReportAddQO socialReportAddQO, UniContentUnionIdDO uniContentUnionIdDO, BaseModelDO modelDO, Integer mineUserId, Integer devId) {
        //这里之后才能校验

        // 设置model

        //这里之后才能校验
        // 设置model
        //可以放到 report的 store 中
        ReportDO reportDO = reportFactory.createReportDO(socialReportAddQO.getContent(), uniContentUnionIdDO, modelDO, ReportSourceType.userReport, devId);

        //保存数据
        reportDO = reportRepository.save(reportDO);

        //生成举报详情
        ReportDetailDO reportDetailDO =
                new ReportDetailDO(socialReportAddQO.getContent(), socialReportAddQO.getReportType(), reportDO, uniContentUnionIdDO, modelDO, mineUserId);

        reportDetailRepository.save(reportDetailDO);

        ResultRO<String> resultRO = new ResultRO<>();
        resultRO.setData(ErrorMsg.reportSubmit);
        if (modelDO != null) {
            //只有用户举报的才修改用户状态
            Integer receiveUserId = modelDO.getUserId();

            //系统自动审查，则只修改动态为预审查


            //用户举报其他用户的逻辑
            SocialuniUserDO receiveUser = SocialuniUserUtil.getUserNotNull(receiveUserId);


            Integer modelReportNum = modelDO.getReportNum() + 1;
            modelDO.setReportNum(modelReportNum);
            //被1个人举报就进入审核中,这里做判断是因为阀值以后可能会调整
            Integer reportCountHide = SocialuniAppConfig.getAppMoreConfig().getReportCountHide();
            //大于阀值，更改动态和用户状态，否则只增加举报此数
            if (modelReportNum >= reportCountHide) {
                modelDO.setStatus(ContentStatus.audit);
                //如果被举报的用户是官方，则不修改官方的用户状态、只存在于官方自己举报自己时，也不能修改自己的用户状态
                if (!receiveUser.getType().equals(UserType.system)) {
                    //只有用户为正常时，才改为待审核，如果用户已被封禁则不改变状态
                    if (receiveUser.getStatus().equals(UserStatus.enable)) {
                        receiveUser.setStatus(UserStatus.audit);
                    }
                }
                //记录用户的被举报此数
                resultRO.setData(ErrorMsg.reportSubmitHide);
            }
            //todo 存到userDetail表
//        receiveUser.setReportNum(receiveUser.getReportNum() + 1);

            userRepository.save(receiveUser);

            //有关
            //必须要单独保存，涉及到缓存
            //被1个人举报就进入审核中,这里做判断是因为阀值以后可能会调整
            //更新 model
            modelDO.setUpdateTime(new Date());
            baseModelService.save(modelDO);
        }

//     todo  测试，不在这里保存，使用 report 的级联保存是否可以
//        baseModelService.save(modelDO);

        // 校验是否触发关键词
        /*List<KeywordsTriggerDetailDO> keywordsTriggers = keywordsTriggerService
                .checkContentTriggerKeywords(modelDO, ReportContentType.comment);

        String reportCause = "系统自动审查";

        Integer reportId = reportDO.getId();

        //为触发记录关联 report
        keywordsTriggers.forEach(keywordsTriggerDetailDO -> {
            keywordsTriggerDetailDO.setReportId(reportId);
        });

        //保存触发记录
        keywordsTriggerDetailRepository.saveAll(keywordsTriggers);*/

        //必须要单独保存，涉及到缓存
        return resultRO;
    }
}
