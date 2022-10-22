package com.socialuni.social.sdk.logic.domain.report;

import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.constant.ReportSourceType;
import com.socialuni.social.sdk.constant.ViolateType;
import com.socialuni.social.sdk.constant.config.AppConfigStatic;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.report.sdk.model.ReportModel;
import com.socialuni.social.report.sdk.model.ReportDetailModel;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgModel;
import com.socialuni.social.sdk.dao.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import com.socialuni.social.sdk.dao.repository.KeywordsTriggerDetailRepository;
import com.socialuni.social.report.sdk.api.ReportDetailApi;
import com.socialuni.social.report.sdk.api.ReportApi;
import com.socialuni.social.user.sdk.api.UserApi;
import com.socialuni.social.sdk.dao.utils.content.SocialuniContentDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.logic.factory.ReportFactory;
import com.socialuni.social.sdk.logic.service.KeywordsTriggerService;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
@Slf4j
public class SoicialuniSystemPreCheckReportDomainDOUtil {
    private static ReportApi reportApi;
    private static ReportDetailApi reportDetailApi;
    private static KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;
    private static KeywordsTriggerService keywordsTriggerService;
    private static UserApi userApi;

    @Resource
    public void setReportRepository(ReportApi reportApi) {
        SoicialuniSystemPreCheckReportDomainDOUtil.reportApi = reportApi;
    }

    @Resource
    public void setReportDetailRepository(ReportDetailApi reportDetailApi) {
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

    @Resource
    public void setUserRepository(UserApi userApi) {
        SoicialuniSystemPreCheckReportDomainDOUtil.userApi = userApi;
    }

    public static void systemPreCheckReport(SocialUnionContentBaseDO socialuniContentBO) {
        CompletableFuture.runAsync(() -> {
            String content = socialuniContentBO.getContent();
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

            //获取唯一标识
            SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(contentId);
            ReportModel reportModel = reportApi.findOneByContentId(contentId);
            //如果不存在则创建
            if (reportModel == null) {
                reportModel = ReportFactory.createReportDO(ReportSourceType.systemAutoCheck, socialuniContentBO, socialuniUnionIdModler);
            }
            reportModel.setReportNum(reportModel.getReportNum() + 1);
            reportModel.setUpdateTime(new Date());
            reportModel = reportApi.savePut(reportModel);
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
            ReportDetailModel reportDetailModel = new ReportDetailModel("系统自动审查", ViolateType.pornInfo, reportModel, content, SocialuniSystemConst.getSystemUserId());

            reportDetailApi.savePut(reportDetailModel);

            Integer reportId = reportModel.getId();

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
                SocialuniTalkImgModel socialTalkImgDO = (SocialuniTalkImgModel) socialUnionContentBaseDO;
                Integer talkId = socialTalkImgDO.getTalkId();
                SocialuniTalkModel talkDO = SocialuniTalkDOUtil.getTalkNotNull(talkId);
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
        }).exceptionally(e -> {
            log.error(String.valueOf(e));
            return null;
        });
    }
}
