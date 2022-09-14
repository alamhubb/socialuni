package com.socialuni.sdk.logic.domain.report;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.dao.repository.*;
import com.socialuni.sdk.dao.DO.UniContentUnionIdDO;
import com.socialuni.sdk.dao.DO.base.BaseModelDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.SocialReportAddQO;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.logic.service.content.ModelContentCheck;
import com.socialuni.sdk.utils.DateUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.ContentType;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
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
    private ReportRepository reportRepository;
    @Resource
    private ReportDetailRepository reportDetailRepository;
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private UserImgRepository userImgRepository;
    @Resource
    private MessageRepository messageRepository;

    @Resource
    private ReportDomain reportDomain;
    @Resource
    private ModelContentCheck modelContentCheck;

    public ResultRO<String> addReport(SocialReportAddQO reportAddVO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        //校验举报类型
        String reportType = reportAddVO.getReportType();
        if (!ViolateType.violateTypes.contains(reportType)) {
            throw new SocialParamsException("错误的举报类型");
        }
        String reportContentType = reportAddVO.getReportContentType();
        if (!ContentType.reportContentTypeTypes.contains(reportContentType)) {
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
            modelContentCheck.checkUser(mineUser);
            //校验内容是否违规
            /*if (resultRO.hasError()) {
                return new ResultRO<>(resultRO);
            }*/

            Date todayZero = DateUtils.getTodayZeroDate();
            Date curDate = new Date();
            /*Integer userJusticeValue = user.getJusticeValue();
            //小于-100不允许再举报
            if (userJusticeValue <= AppConfigConst.cantReportValue) {
                return new ResultRO<>("您涉嫌胡乱举报，被禁止使用举报功能");
                //小于-10一天只能举报两次
            }*/
            Integer reportCount = reportDetailRepository.countByUserIdAndCreateTimeBetween(mineUser.getUnionId(), todayZero, curDate);
           /* if (userJusticeValue < AppConfigConst.limitReportValue) {
                if (reportCount >= AppConfigConst.lowLimitReportCount) {
                    return new ResultRO<>("因您的正义值低于：" + AppConfigConst.limitReportValue + "，所以您每天只能举报：" + AppConfigConst.lowLimitReportCount + "次");
                }
            }*/
            Integer highLimitReportCount = SocialuniAppConfig.appMoreConfig.getHighLimitReportCount();
            if (reportCount >= highLimitReportCount) {
                throw new SocialParamsException("每人每天只能举报" + highLimitReportCount + "次");
            }
        }
        //现在这个意思是，只能举报，正常显示的动态，待审核，不违规，预审核，删除的都举报不了
        //理论上来说这些状态应该都是不能被举报的，因为看不见，所以这个逻辑现在也没啥问题，没了不违规状态了
        //查询动态状态是否为正常
        BaseModelDO modelDO = null;
        // 举报了动态
        // 举报了动态
        if (ContentType.talk.equals(reportContentType)) {
            //查询出 评论信息
            //只查询正常能看到的，违规，审核，删除的都提示
            modelDO = talkRepository.findOneByUnionId(reportAddVO.getContentId());
            if (modelDO != null && !modelDO.getStatus().equals(ContentStatus.enable)) {
                modelDO = null;
            }
        } else if (ContentType.comment.equals(reportContentType)) {
            modelDO = commentRepository.findOneByUnionIdAndStatus(reportAddVO.getContentId(), ContentStatus.enable);
        } else if (ContentType.message.equals(reportContentType)) {
            modelDO = messageRepository.findOneByUnionIdAndStatus(reportAddVO.getContentId(), ContentStatus.enable);
        } else if (ContentType.userImg.equals(reportContentType)) {
            modelDO = userImgRepository.findOneByUnionIdAndStatus(reportAddVO.getContentId(), ContentStatus.enable);
        } else {
            throw new SocialParamsException("错误的内容类型");
        }
        if (modelDO == null) {
            throw new SocialBusinessException("内容已被举报，审核中");
        }

        UniContentUnionIdDO uniContentUnionIdDO = UnionIdDbUtil.getUnionDOByUnionIdNotNull(reportAddVO.getContentId());

        //则代表这条数据不属于本系统
        //这里限制了内容只能被举报一次，只有第一次被举报时，修改用户状态和动态状态
        //之后，只有审核时，才修改动态，
        //不为正常则不该看到，提示已被举报，有点问题不影响业务的小问题，提示信息不对
        if (!ContentStatus.enable.equals(uniContentUnionIdDO.getStatus())) {
            throw new SocialParamsException("内容已被举报，审核中");
        }
        Integer receiveUserUnionId = modelDO.getUserId();
        //这里之后才能校验
        SocialuniUserDO receiveUser = SocialuniUserUtil.getAllowNull(receiveUserUnionId);

        //本系统user,才进行相关校验
        if (receiveUser != null) {
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
        }

        return reportDomain.userReportContent(
                reportAddVO,
                uniContentUnionIdDO,
                modelDO,
                mineUser.getUnionId(), uniContentUnionIdDO.getFromDevId()
        );
    }

}
