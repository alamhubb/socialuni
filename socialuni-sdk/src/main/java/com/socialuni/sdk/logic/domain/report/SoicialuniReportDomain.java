package com.socialuni.sdk.logic.domain.report;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.constant.ErrorMsg;
import com.socialuni.sdk.constant.ReportSourceType;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.constant.config.AppConfigStatic;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.DO.ReportDetailDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.sdk.dao.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.repository.KeywordsTriggerDetailRepository;
import com.socialuni.sdk.dao.repository.ReportDetailRepository;
import com.socialuni.sdk.dao.repository.ReportRepository;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.dao.utils.content.SocialuniContentDOUtil;
import com.socialuni.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.sdk.logic.factory.ReportFactory;
import com.socialuni.sdk.logic.service.KeywordsTriggerService;
import com.socialuni.sdk.model.QO.SocialuniReportAddQO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
public class SoicialuniReportDomain {
    private static ReportRepository reportRepository;
    private static ReportDetailRepository reportDetailRepository;
    private static KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;
    private static KeywordsTriggerService keywordsTriggerService;
    private static UserRepository userRepository;

    @Resource
    public void setReportRepository(ReportRepository reportRepository) {
        SoicialuniReportDomain.reportRepository = reportRepository;
    }

    @Resource
    public void setReportDetailRepository(ReportDetailRepository reportDetailRepository) {
        SoicialuniReportDomain.reportDetailRepository = reportDetailRepository;
    }

    @Resource
    public void setKeywordsTriggerDetailRepository(KeywordsTriggerDetailRepository keywordsTriggerDetailRepository) {
        SoicialuniReportDomain.keywordsTriggerDetailRepository = keywordsTriggerDetailRepository;
    }

    @Resource
    public void setKeywordsTriggerService(KeywordsTriggerService keywordsTriggerService) {
        SoicialuniReportDomain.keywordsTriggerService = keywordsTriggerService;
    }

    @Resource
    public void setUserRepository(UserRepository userRepository) {
        SoicialuniReportDomain.userRepository = userRepository;
    }

    public static void systemPreCheckReport(SocialUnionContentBaseDO socialuniContentBO) {
        CompletableFuture.runAsync(() -> {
            String content = socialuniContentBO.getContentType();
            //不为空才校验内容
            if (StringUtils.isEmpty(content)) {
                return;
            }
            //网易三方审查
//            AntispamDO antispamDO = WangYiUtil.checkWYContentSecPost(modelDO);
            //校验有没有触发系统的敏感词
            // 校验是否触发关键词
            List<KeywordsTriggerDetailDO> keywordsTriggers = keywordsTriggerService.checkContentTriggerKeywords(socialuniContentBO, AppConfigStatic.getKeywordDOs(), false);
            //没有触发返回
            if (CollectionUtils.isEmpty(keywordsTriggers)) {
                return;
            }
            Integer contentId = socialuniContentBO.getUnionId();

            ReportDO reportDO = reportRepository.findOneByContentId(contentId);
            //如果不存在则创建
            if (reportDO == null) {
                reportDO = ReportFactory.createReportDO(ReportSourceType.systemAutoCheck, socialuniContentBO);
            }
            reportDO.setReportNum(reportDO.getReportNum() + 1);
            reportDO.setUpdateTime(new Date());
            reportDO = reportRepository.save(reportDO);

            //得到系统的
            //如果触发了关键词
            /*    if (antispamDO.hasViolate()) {
                    reportCause = antispamDO.getCause();
                    reportDO = reportFactory.createReportDO(reportCause, modelDO, ReportSourceType.antispam, antispamDO.getId());
                } else {*/
//                }

            //这里之后才能校验
            // 设置model
            //可以放到 report的 store 中
            //保存数据

            //生成举报详情
            ReportDetailDO reportDetailDO = new ReportDetailDO("系统自动审查", ViolateType.pornInfo, reportDO, content, SocialuniSystemConst.getSystemUserId());

            reportDetailRepository.save(reportDetailDO);


            Integer reportId = reportDO.getId();

            //为触发记录关联 report
            keywordsTriggers.forEach(keywordsTriggerDetailDO -> {
                keywordsTriggerDetailDO.setReportId(reportId);
            });

            //保存触发记录
            keywordsTriggerDetailRepository.saveAll(keywordsTriggers);

            SocialUnionContentBaseDO socialUnionContentBaseDO = SocialuniContentDOUtil.getContentDOByContentId(contentId);

            socialUnionContentBaseDO.setStatus(ContentStatus.preAudit);
            socialUnionContentBaseDO.setUpdateTime(new Date());
            //更新 model
            SocialuniContentDOUtil.save(socialUnionContentBaseDO);
            if (socialuniContentBO.getContentType().equals(SocialuniContentType.talkImg)) {
                //同步更新talk状态
                SocialuniTalkImgDO socialTalkImgDO = (SocialuniTalkImgDO) socialUnionContentBaseDO;
                Integer talkId = socialTalkImgDO.getTalkId();
                SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(talkId);
                talkDO.setStatus(ContentStatus.preAudit);
                talkDO.setUpdateTime(new Date());
                SocialuniContentDOUtil.save(socialUnionContentBaseDO);
            } else if (socialuniContentBO.getContentType().equals(SocialuniContentType.userImg)) {
                //user预审核有什么用？,缺少相关逻辑所以暂时注释掉
            /*SocialuniUserImgDO socialUserImgDO = (SocialuniUserImgDO) socialUnionContentBaseDO;
            Integer userId = socialUserImgDO.getUserId();
            SocialuniUserDO userDO = SocialuniUserUtil.getUserNotNull(userId);
            userDO.setStatus(ContentStatus.preAudit);
            userDO.setUpdateTime(new Date());
            baseModelService.save(socialUnionContentBaseDO);*/
            }
        });
    }


    @Transactional
    public ResultRO<String> userReportContent(SocialuniReportAddQO socialReportAddQO, SocialUnionContentBaseDO modelDO) {
        //这里之后才能校验

        // 设置model

        //这里之后才能校验
        // 设置model
        //可以放到 report的 store 中
        ReportDO reportDO = ReportFactory.createReportDO(ReportSourceType.userReport, modelDO);

        //保存数据
        reportDO = reportRepository.save(reportDO);

        //生成举报详情
        ReportDetailDO reportDetailDO = new ReportDetailDO(socialReportAddQO.getContent(), socialReportAddQO.getReportType(), reportDO, modelDO.getContent(), SocialuniSystemConst.getSystemUserId());

        reportDetailRepository.save(reportDetailDO);

        ResultRO<String> resultRO = new ResultRO<>();
        resultRO.setData(ErrorMsg.reportSubmit);
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
        receiveUser.setReportNum(receiveUser.getReportNum() + 1);
//       非用户自身操作不更改时间 receiveUser.setUpdateTime(new Date());
        userRepository.save(receiveUser);

        //有关
        //必须要单独保存，涉及到缓存
        //被1个人举报就进入审核中,这里做判断是因为阀值以后可能会调整
        //更新 model
        modelDO.setUpdateTime(new Date());
        SocialuniContentDOUtil.save(modelDO);

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
