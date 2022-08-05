package com.socialuni.center.web.service;

import com.socialuni.center.web.constant.AppConfigConst;
import com.socialuni.center.web.constant.ReportSourceType;
import com.socialuni.center.web.factory.ReportFactory;
import com.socialuni.center.web.repository.*;
import com.socialuni.social.constant.ReportStatus;
import com.socialuni.social.entity.model.DO.JusticeValueOrderDO;
import com.socialuni.social.entity.model.DO.ReportDO;
import com.socialuni.social.entity.model.DO.ReportDetailDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.center.web.repository.*;
import com.socialuni.center.web.utils.ReportDetailUtils;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.social.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
public class ReportService {
    @Resource
    private ReportDetailRepository reportDetailRepository;
    @Resource
    private JusticeValueOrderRepository justiceValueOrderRepository;

    @Resource
    private ReportRepository reportRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private KeywordsRepository keywordsRepository;
    @Resource
    private KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;

    @Resource
    private KeywordsTriggerService keywordsTriggerService;

    @Resource
    private KeywordsService keywordsService;

    @Resource
    private ReportFactory reportFactory;

    /**
     * 动态被2人以上举报，则用户自己也看不见了。两人以下举报，用户可自行删除
     * <p>
     * 管理直接发现，未通过审核平台，
     * <p>
     * 首先确认talk 是否真实
     * <p>
     * 根据talk查询是否存在相关举报
     * <p>
     * 判断用户有没有举报记录，有举报记录，走举报，没有走直接封禁
     *
     * @param
     */
    public void reportPass(ReportDO reportDO, boolean isViolation) {
        if (ReportSourceType.userReport.equals(reportDO.getReportSourceType())) {

            Date curDate = new Date();
            //审核通过不再接受举报，前台点击举报时，提示已官方审核通过
            List<ReportDetailDO> reportDetailDOS = ReportDetailUtils.getAll(reportDO.getId());

            //变更detail
            for (ReportDetailDO reportDetailDO : reportDetailDOS) {
                UserDO detailUser = SocialUserUtil.getNotNull(reportDetailDO.getUserId());

                //相同部分
                JusticeValueOrderDO justiceValueOrderDO = new JusticeValueOrderDO();

                //举报内容违规，加分
                if (isViolation) {
                    Date todayZero = DateUtils.getTodayZeroDate();
                    //查看用户待审核的举报数量
                    Integer reportSuccessCount = reportDetailRepository.countByUserIdAndStatusNotAndCreateTimeBetween(detailUser.getId(), ReportStatus.audit, todayZero, curDate);
                    //todo  缺少发送通知功能，等我精神好了在写
                    reportDetailDO.setStatus(ReportStatus.violation);
                    if (reportSuccessCount > 9) {
                        //todo 发送通知
                    } else {
                        justiceValueOrderDO.setJusticeValue(AppConfigConst.auditSuccessValue);
//                        detailUser.setJusticeValue(detailUser.getJusticeValue() + AppConfigConst.auditSuccessValue);
                        //user加分
//                        if (reportDetailDO.getReportType().equals(auditVO.getReportType())) {
                        //目前不区分举报类型
                        //如果今天已经成功举报了10个以上，则不再发放奖励
                        //默认不奖励

                        /*} else {
                            detailUser.setJusticeValue(detailUser.getJusticeValue() + CommonConst.reportTypeMistakeValue);
                        }*/
                        //发送通知
                    }
                } else {
                    //如果今天已经成功举报了10个以上，则不再发放奖励
                    reportDetailDO.setStatus(ReportStatus.enable);
                    //错误的举报，user减分
                    justiceValueOrderDO.setJusticeValue(-AppConfigConst.reportErrorValue);
//                    detailUser.setJusticeValue(detailUser.getJusticeValue() - AppConfigConst.reportErrorValue);
                }
                justiceValueOrderDO.setCreateTime(curDate);
                justiceValueOrderDO.setUserId(detailUser.getId());
                justiceValueOrderDO.setReportDetailId(reportDetailDO.getId());

                justiceValueOrderRepository.save(justiceValueOrderDO);


                //不允许重复举报
                //应该查询今天奖励了多少次了
                //如果类型一直加10分
                //


                //不允许重复举报
                //应该查询今天奖励了多少次了
                //如果类型一直加10分
                //
            }
        }
    }
}
