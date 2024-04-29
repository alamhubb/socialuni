package com.socialuni.social.report.sdk.utils;

import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.common.sdk.utils.DateUtils;
import com.socialuni.social.content.constant.SocialuniSupportProviderType;
import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.report.sdk.dao.repository.ReportRepository;
import com.socialuni.social.common.sdk.constant.ErrorMsg;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.common.sdk.platform.qq.QQConst;
import com.socialuni.social.common.sdk.platform.weixin.WxConst;
import com.socialuni.social.common.sdk.platform.PushMsgDTO;
import com.socialuni.social.sdk.im.notify.PushNotifyVO;
import com.socialuni.social.sdk.im.notify.PushValue;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author qinkaiyuan
 * @date 2020-03-22 2:04
 */
@Component
public class ReportResultPushUtils {
    private static ReportRepository reportApi;

    @Resource
    public void setReportRepository(ReportRepository reportApi) {
        ReportResultPushUtils.reportApi = reportApi;
    }

    //动态评论通知
    public static PushMsgDTO getReportResultPushDTO(String provider, NotifyDO notify) {
        ReportDO ReportDO = reportApi.findById(notify.getContentId()).get();
        SocialuniUnionContentBaseDO baseModelDO = SocialuniContentDOUtil.getContentDOByContentId(ReportDO.getContentId());

        //举报原因
        String reportCause = ReportDO.getAuditType();
        if (StringUtils.isNotEmpty(ReportDO.getAuditNote())) {
            reportCause = reportCause + "，" + ReportDO.getAuditNote();
        }

        String reportResult = ErrorMsg.report_fail_result;
        String reportRemark = ErrorMsg.report_fail_remark;
        if (ReportDO.getValid()) {
            reportResult = ErrorMsg.report_success_result;
            reportRemark = ErrorMsg.report_success_remark;
        }

        PushNotifyVO notifyVO = new PushNotifyVO();
        notifyVO.setNickname(new PushValue(ErrorMsg.serviceName));
        notifyVO.setBeContent(new PushValue(StringUtils.substring(baseModelDO.getContent(), 0, 20)));
        notifyVO.setBeNickname(new PushValue(SocialuniUserUtil.getAndCheckUserNotNull(baseModelDO.getUserId()).getNickname()));
        notifyVO.setCause(new PushValue(reportCause));
        notifyVO.setResult(new PushValue(reportResult));
        notifyVO.setDate(new PushValue(DateUtils.simpleTimeFormat.format(ReportDO.getUpdateTime())));
        notifyVO.setRemark(new PushValue(StringUtils.substring(reportRemark, 0, 20)));

        HashMap<String, Object> data = new HashMap<>();
        PushMsgDTO pushMsgDTO = null;
        if (provider.equals(SocialuniSupportProviderType.qq)) {
            //举报内容
            data.put("keyword5", notifyVO.getBeContent());
            //被举报人
            data.put("keyword6", notifyVO.getBeNickname());
            //举报原因
            data.put("keyword3", notifyVO.getCause());
            //处理结果
            data.put("keyword4", notifyVO.getResult());
            //处理时间
            data.put("keyword1", notifyVO.getDate());
            //备注
            data.put("keyword2", notifyVO.getRemark());

            pushMsgDTO = new PushMsgDTO(QQConst.report_result_template_id, data, "keyword4.DATA");
        } else if (provider.equals(SocialuniSupportProviderType.wx)) {
            //审核内容
            data.put("thing5", notifyVO.getBeContent());
            //审核结果
            data.put("phrase1", notifyVO.getResult());
            //删除时间
            data.put("date4", notifyVO.getDate());
            //审核人
            data.put("name7", notifyVO.getNickname());
            //备注
            data.put("thing8", notifyVO.getRemark());

            pushMsgDTO = new PushMsgDTO(WxConst.report_result_template_id, data);
            //新的版本
        }
        return pushMsgDTO;
    }
}
