package com.socialuni.social.report.sdk.utils;

import com.socialuni.social.content.logic.service.KeywordsTriggerService;
import com.socialuni.social.report.sdk.dao.repository.ReportDetailRepository;
import com.socialuni.social.report.sdk.dao.repository.ReportRepository;
import com.socialuni.social.content.dao.repository.KeywordsTriggerDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
@Slf4j
public class SoicialuniSystemPreCheckReportDomainDOUtil {
    private static ReportRepository reportApi;
    private static ReportDetailRepository reportDetailApi;
    private static KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;
    private static KeywordsTriggerService keywordsTriggerService;

    @Resource
    public void setReportRepository(ReportRepository reportApi) {
        SoicialuniSystemPreCheckReportDomainDOUtil.reportApi = reportApi;
    }

    @Resource
    public void setReportDetailRepository(ReportDetailRepository reportDetailApi) {
        SoicialuniSystemPreCheckReportDomainDOUtil.reportDetailApi = reportDetailApi;
    }

    @Resource
    public void setKeywordsTriggerDetailRepository(KeywordsTriggerDetailRepository keywordsTriggerDetailRepository) {
        SoicialuniSystemPreCheckReportDomainDOUtil.keywordsTriggerDetailRepository = keywordsTriggerDetailRepository;
    }

    @Resource
    public void setKeywordsTriggerService(KeywordsTriggerService keywordsTriggerService) {
        SoicialuniSystemPreCheckReportDomainDOUtil.keywordsTriggerService = keywordsTriggerService;
    }

    /*public static void systemPreCheckReport(SocialuniUnionContentBaseDO socialuniContentBO) {
        CompletableFuture.runAsync(() -> {
            String platform = RequestUtil.getPlatform();
            String provider = RequestUtil.getProvider();
            String content = socialuniContentBO.getContent();
            List<KeywordsTriggerDetailDO> keywordsTriggers = new ArrayList<>();
            //如果系统处于审核中，则发布的状态需要设置为预审核，所以不走退出逻辑
            if (SocialuniAppConfig.getAppMoreConfig().getMp_wx_auditing() && MpPlatformType.isMpWx(platform, provider)) {

            } else if (SocialuniAppConfig.getAppMoreConfig().getMp_qq_auditing() && MpPlatformType.isMpQQ(platform, provider)) {

            } else {

                //不为空才校验内容
                if (StringUtils.isEmpty(content)) {
                    return;
                }
                //不为审核中，且违规为0才退出
                //网易三方审查
//            AntispamDO antispamDO = WangYiUtil.checkWYContentSecPost(modelDO);
                //校验有没有触发系统的敏感词
                // 校验是否触发关键词
                keywordsTriggers = keywordsTriggerService.checkContentTriggerKeywords(socialuniContentBO, AppConfigStatic.getKeywordDOs(), false);
                //没有触发返回
                if (CollectionUtils.isEmpty(keywordsTriggers)) {
                    return;
                }
            }
            Long contentId = socialuniContentBO.getUnionId();

            //获取唯一标识
            SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(contentId);
            ReportDO ReportDO = reportApi.findOneByContentId(contentId);
            //如果不存在则创建
            if (ReportDO == null) {
                ReportDO = ReportFactory.createReportDO(ReportSourceType.systemAutoCheck, socialuniContentBO, socialuniUnionIdModler);
            }
            ReportDO.setReportNum(ReportDO.getReportNum() + 1);
            ReportDO.setUpdateTime(new Date());
            ReportDO = reportApi.savePut(ReportDO);
            //得到系统的
            //如果触发了关键词
            *//*    if (antispamDO.hasViolate()) {
                    reportCause = antispamDO.getCause();
                    reportDO = reportFactory.createReportDO(reportCause, modelDO, ReportSourceType.antispam, antispamDO.getId());
                } else {*//*
//                }

            //这里之后才能校验
            // 设置model
            //可以放到 report的 store 中
            //保存数据

            //生成举报详情
            ReportDetailDO ReportDetailDO = new ReportDetailDO("系统自动审查", ViolateType.pornInfo, ReportDO, content, DevAccountFacade.getDevUserId());

            reportDetailApi.savePut(ReportDetailDO);

            Integer reportId = ReportDO.getId();

            //为触发记录关联 report
            keywordsTriggers.forEach(keywordsTriggerDetailDO -> {
                keywordsTriggerDetailDO.setReportId(reportId);
            });
            //保存触发记录
            keywordsTriggerDetailRepository.saveAll(keywordsTriggers);
            SocialuniUnionContentBaseDO socialUnionContentBaseDO = SocialuniContentDOUtil.getContentDOByContentId(contentId);

            socialUnionContentBaseDO.setStatus(ContentStatus.preAudit);
            socialUnionContentBaseDO.setUpdateTime(new Date());
            //更新 model
            SocialuniContentDOUtil.save(socialUnionContentBaseDO);
            if (socialuniContentBO.getContentType().equals(SocialuniContentType.talkImg)) {
                //同步更新talk状态
                SocialuniTalkImgDO socialTalkImgDO = (SocialuniTalkImgDO) socialUnionContentBaseDO;
                Long talkId = socialTalkImgDO.getTalkId();
                SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(talkId);
                talkDO.setStatus(ContentStatus.preAudit);
                talkDO.setUpdateTime(new Date());
                SocialuniContentDOUtil.save(socialUnionContentBaseDO);
            } else if (socialuniContentBO.getContentType().equals(SocialuniContentType.userImg)) {
                //user预审核有什么用？,缺少相关逻辑所以暂时注释掉
            *//*SocialuniUserImgDO socialUserImgDO = (SocialuniUserImgDO) socialUnionContentBaseDO;
            Long userId = socialUserImgDO.getUserId();
            SocialuniUserDo userDO = SocialuniUserUtil.getUserNotNull(userId);
            userDO.setStatus(ContentStatus.preAudit);
            userDO.setUpdateTime(new Date());
            baseModelService.save(socialUnionContentBaseDO);*//*
            }
        }).exceptionally(e -> {
            log.error(String.valueOf(e));
            return null;
        });
    }*/
}
