package com.socialuni.sdk.logic.domain.report;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.constant.ReportSourceType;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.constant.config.AppConfigStatic;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.DO.ReportDetailDO;
import com.socialuni.sdk.dao.DO.SocialuniUnionIdDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.sdk.dao.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.sdk.dao.repository.KeywordsTriggerDetailRepository;
import com.socialuni.sdk.dao.repository.ReportDetailRepository;
import com.socialuni.sdk.dao.repository.ReportRepository;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.dao.utils.content.SocialuniContentDOUtil;
import com.socialuni.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.sdk.logic.factory.ReportFactory;
import com.socialuni.sdk.logic.service.KeywordsTriggerService;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
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
public class SoicialuniSystemPreCheckReportDomainDOUtil {
    private static ReportRepository reportRepository;
    private static ReportDetailRepository reportDetailRepository;
    private static KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;
    private static KeywordsTriggerService keywordsTriggerService;
    private static UserRepository userRepository;

    @Resource
    public void setReportRepository(ReportRepository reportRepository) {
        SoicialuniSystemPreCheckReportDomainDOUtil.reportRepository = reportRepository;
    }

    @Resource
    public void setReportDetailRepository(ReportDetailRepository reportDetailRepository) {
        SoicialuniSystemPreCheckReportDomainDOUtil.reportDetailRepository = reportDetailRepository;
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
    public void setUserRepository(UserRepository userRepository) {
        SoicialuniSystemPreCheckReportDomainDOUtil.userRepository = userRepository;
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

            //获取唯一标识
            SocialuniUnionIdDO socialuniUnionIdDO = SocialuniUnionIdUtil.getUnionDOByUnionIdNotNull(contentId);

            ReportDO reportDO = reportRepository.findOneByContentId(contentId);
            //如果不存在则创建
            if (reportDO == null) {
                reportDO = ReportFactory.createReportDO(ReportSourceType.systemAutoCheck, socialuniContentBO, socialuniUnionIdDO);
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
}
