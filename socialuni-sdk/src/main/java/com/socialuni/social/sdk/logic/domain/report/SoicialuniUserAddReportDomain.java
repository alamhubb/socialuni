package com.socialuni.social.sdk.logic.domain.report;

import com.socialuni.social.sdk.config.SocialuniAppConfig;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.sdk.constant.ReportSourceType;
import com.socialuni.social.sdk.constant.UserType;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.report.sdk.model.ReportModel;
import com.socialuni.social.report.sdk.model.ReportDetailModel;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.report.sdk.api.ReportDetailApi;
import com.socialuni.social.report.sdk.api.ReportApi;
import com.socialuni.social.user.sdk.api.UserApi;
import com.socialuni.social.sdk.dao.utils.content.SocialuniContentDOUtil;
import com.socialuni.social.sdk.logic.factory.ReportFactory;
import com.socialuni.social.sdk.model.QO.SocialuniReportAddQO;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
public class SoicialuniUserAddReportDomain {
    @Resource
    private ReportApi reportApi;
    @Resource
    private ReportDetailApi reportDetailApi;
    @Resource
    private UserApi userApi;

    @Transactional
    public ResultRO<String> userReportContent(SocialuniReportAddQO socialReportAddQO, SocialUnionContentBaseDO modelDO) {
        //这里之后才能校验

        Integer contentId = modelDO.getUnionId();

        SocialuniUnionIdModler uniContentUnionIdDO = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(contentId);
        // 设置model

        ReportModel reportModel = reportApi.findOneByContentId(contentId);
        if (reportModel == null) {
            reportModel = ReportFactory.createReportDO(ReportSourceType.userReport, modelDO, uniContentUnionIdDO);
        }
        reportModel.setReportNum(reportModel.getReportNum() + 1);
        reportModel.setUpdateTime(new Date());
        //保存数据
        reportModel = reportApi.savePut(reportModel);

        //生成举报详情
        ReportDetailModel reportDetailModel = new ReportDetailModel(socialReportAddQO.getContent(), socialReportAddQO.getReportType(), reportModel, modelDO.getContent(), SocialuniSystemConst.getSystemUserId());

        reportDetailApi.savePut(reportDetailModel);

        ResultRO<String> resultRO = new ResultRO<>();
        resultRO.setData(ErrorMsg.reportSubmit);
        //只有用户举报的才修改用户状态
        Integer receiveUserId = modelDO.getUserId();

        //系统自动审查，则只修改动态为预审查


        //用户举报其他用户的逻辑
        SocialuniUserModel receiveUser = SocialuniUserUtil.getUserNotNull(receiveUserId);


        Integer modelReportNum = modelDO.getReportNum() + 1;
        modelDO.setReportNum(modelReportNum);
        //被1个人举报就进入审核中,这里做判断是因为阀值以后可能会调整
        Integer reportCountHide = SocialuniAppConfig.getAppMoreConfig().getReportCountHide();
        //大于阀值，更改动态和用户状态，否则只增加举报此数
        if (modelReportNum >= reportCountHide) {
            modelDO.setStatus(ContentStatus.auditing);
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
//       非用户自身操作不更改时间 receiveUser.setUpdateTime(new Date());
        userApi.savePut(receiveUser);

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
