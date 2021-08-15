package com.socialuni.social.sdk.domain.report;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.base.BaseModelDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.SocialReportAddQO;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.sdk.constant.UserType;
import com.socialuni.social.sdk.constant.ViolateType;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.sdk.repository.*;
import com.socialuni.social.sdk.service.content.ModelContentCheck;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

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

    public ResultRO<Void> addReport(UserDO mineUser, SocialReportAddQO reportAddVO) {
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
            Integer reportCount = reportDetailRepository.countByUserIdAndCreateTimeBetween(mineUser.getId(), todayZero, curDate);
           /* if (userJusticeValue < AppConfigConst.limitReportValue) {
                if (reportCount >= AppConfigConst.lowLimitReportCount) {
                    return new ResultRO<>("因您的正义值低于：" + AppConfigConst.limitReportValue + "，所以您每天只能举报：" + AppConfigConst.lowLimitReportCount + "次");
                }
            }*/
            Integer highLimitReportCount = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.highLimitReportCountKey);
            if (reportCount >= highLimitReportCount) {
                throw new SocialParamsException("每人每天只能举报" + highLimitReportCount + "次");
            }
        }
        //现在这个意思是，只能举报，正常显示的动态，待审核，不违规，预审核，删除的都举报不了
        //理论上来说这些状态应该都是不能被举报的，因为看不见，所以这个逻辑现在也没啥问题，没了不违规状态了
        //查询动态状态是否为正常
        Optional<BaseModelDO> modelOptional;
        // 举报了动态
        // 举报了动态
        if (ContentType.talk.equals(reportContentType)) {
            //查询出 评论信息
            //只查询正常能看到的，违规，审核，删除的都提示
            modelOptional = talkRepository.findTop1ById(reportAddVO.getContentId());
        } else if (ContentType.comment.equals(reportContentType)) {
            modelOptional = commentRepository.findOneByIdAndStatus(reportAddVO.getContentId(), ContentStatus.enable);
        } else if (ContentType.message.equals(reportContentType)) {
            modelOptional = messageRepository.findOneByIdAndStatus(reportAddVO.getContentId(), ContentStatus.enable);
        } else if (ContentType.userImg.equals(reportContentType)) {
            modelOptional = userImgRepository.findOneByIdAndStatus(reportAddVO.getContentId(), ContentStatus.enable);
        } else {
            throw new SocialParamsException("错误的内容类型");
        }
        //这里限制了内容只能被举报一次，只有第一次被举报时，修改用户状态和动态状态
        //之后，只有审核时，才修改动态，
        if (!modelOptional.isPresent()) {
            throw new SocialParamsException("内容已被举报，审核中");
        }
        BaseModelDO modelDO = modelOptional.get();
        //不为正常则不该看到，提示已被举报，有点问题不影响业务的小问题，提示信息不对
        if (!ContentStatus.enable.equals(modelDO.getStatus())) {
            throw new SocialParamsException("内容已被举报，审核中");
        }
        //这里之后才能校验
        UserDO receiveUser = SocialUserUtil.get(modelDO.getUserId());
        //举报人不为系统管理员才校验
        if (!mineUser.getType().equals(UserType.system)) {
            if (UserType.system.equals(receiveUser.getType())) {
                throw new SocialParamsException("不能举报官方内容");
            } else if (mineUser.getId().equals(receiveUser.getId())) {
                throw new SocialParamsException("不能举报自己的评论");
            } /*else if (modelDO.getStatus().equals(CommonStatus.noViolation)) {
                return new ResultVO<>("内容已审核，不违规");
            }*/
        }
        return reportDomain.userReportContent(
                reportAddVO,
                modelDO,
                mineUser.getId()
        );
    }

}
