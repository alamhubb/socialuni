package com.socialuni.social.report.sdk.logic.service;

import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.constant.ViolateType;
import com.socialuni.social.report.sdk.dao.repository.ReportDetailRepository;
import com.socialuni.social.report.sdk.utils.SocialuniContentDOUtil;
import com.socialuni.social.tance.dev.config.SocialuniAppConfig;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.report.sdk.model.SocialuniReportAddQO;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
public class SoicialReportAddDomain {
    @Resource
    private ReportDetailRepository reportDetailApi;
    @Resource
    private SoicialuniUserAddReportDomain soicialuniReportDomain;

    public ResultRO<String> addReport(SocialuniReportAddQO reportAddVO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        //校验举报类型
        String reportType = reportAddVO.getReportType();
        if (!ViolateType.frontShowReportTypes.contains(reportType)) {
            throw new SocialParamsException("错误的举报类型");
        }
        String reportContentType = reportAddVO.getReportContentType();
        if (!SocialuniContentType.reportContentTypeTypes.contains(reportContentType)) {
            throw new SocialParamsException("错误的举报内容类型");
        }
        if (ViolateType.other.equals(reportType) && StringUtils.isEmpty(reportAddVO.getContent())) {
            throw new SocialBusinessException("选择其他违规时，请您补充观点");
        }
        //这点应该在新增举报那个页面能控制，下面这俩
        //正义值小于0，则每天只能举报2个，举报提示
        //正义值小于300不能再举报
        //给用户通知，您举报成功失败，奖励或扣除积分，每天满多少，低于0 2练个，低于200不能再举报
        if (!mineUser.getType().equals(UserType.system)) {
            SocialuniUserCheck.checkUserBindPhoneNumAndStatusEnable();
            //校验内容是否违规
            /*if (resultRO.hasError()) {
                return new ResultRO<>(resultRO);
            }*/

            Date todayZero = SocialuniDateUtils.getTodayZeroDate();
            Date curDate = new Date();
            /*Integer userJusticeValue = user.getJusticeValue();
            //小于-100不允许再举报
            if (userJusticeValue <= AppConfigConst.cantReportValue) {
                return new ResultRO<>("您涉嫌胡乱举报，被禁止使用举报功能");
                //小于-10一天只能举报两次
            }*/
            Integer reportCount = reportDetailApi.countByUserIdAndCreateTimeBetween(mineUser.getUnionId(), todayZero, curDate);
           /* if (userJusticeValue < AppConfigConst.limitReportValue) {
                if (reportCount >= AppConfigConst.lowLimitReportCount) {
                    return new ResultRO<>("因您的正义值低于：" + AppConfigConst.limitReportValue + "，所以您每天只能举报：" + AppConfigConst.lowLimitReportCount + "次");
                }
            }*/
            Integer highLimitReportCount = SocialuniAppConfig.getAppMoreConfig().getHighLimitReportCount();
            if (reportCount >= highLimitReportCount) {
                throw new SocialParamsException("每人每天只能举报" + highLimitReportCount + "次");
            }
        }
        //现在这个意思是，只能举报，正常显示的动态，待审核，不违规，预审核，删除的都举报不了
        //理论上来说这些状态应该都是不能被举报的，因为看不见，所以这个逻辑现在也没啥问题，没了不违规状态了
        //查询动态状态是否为正常

        Long contentUuId = SocialuniUnionIdFacede.createUnionIdByUuid(reportContentType, Long.valueOf(reportAddVO.getContentId()));
        Long  contentUnionId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(contentUuId);

        SocialuniUnionContentBaseDO modelDO = SocialuniContentDOUtil.getContentDOByContentId(contentUnionId);

        Long  receiveUserUnionId = modelDO.getUserId();
        //这里之后才能校验
        SocialuniUserDo receiveUser = SocialuniUserUtil.getUserNotNull(receiveUserUnionId);

        //举报人不为系统管理员才校验
        if (!mineUser.getType().equals(UserType.system)) {
            if (UserType.system.equals(receiveUser.getType())) {
                throw new SocialParamsException("不能举报官方内容");
            } else if (mineUser.getUnionId().equals(receiveUser.getUnionId())) {
                throw new SocialParamsException("不能举报自己的评论");
            } /*else if (modelDO.getStatus().equals(CommonStatus.noViolation)) {
                return new ResultVO<>("内容已审核，不违规");
            }*/
        }

        return soicialuniReportDomain.userReportContent(
                reportAddVO,
                modelDO
        );
    }

}
