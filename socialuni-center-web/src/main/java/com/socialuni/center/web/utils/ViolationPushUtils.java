package com.socialuni.center.web.utils;

import com.socialuni.center.web.constant.ErrorMsg;
import com.socialuni.center.web.constant.platform.UniappProviderType;
import com.socialuni.center.web.model.PushMsgDTO;
import com.socialuni.center.web.model.PushNotifyVO;
import com.socialuni.center.web.model.PushValue;
import com.socialuni.center.web.platform.qq.QQConst;
import com.socialuni.center.web.platform.weixin.WxConst;
import com.socialuni.center.web.service.BaseModelUtils;
import com.socialuni.social.entity.model.DO.NotifyDO;
import com.socialuni.social.entity.model.DO.ReportDO;
import com.socialuni.social.entity.model.DO.base.BaseModelDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.center.web.repository.ReportRepository;
import com.socialuni.social.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * @author qinkaiyuan
 * @date 2020-03-22 2:04
 */
@Component
public class ViolationPushUtils {
    private static ReportRepository reportRepository;

    @Resource
    public void setReportRepository(ReportRepository reportRepository) {
        ViolationPushUtils.reportRepository = reportRepository;
    }

    //动态评论通知
    public static PushMsgDTO getViolationPushDTO(String platform, NotifyDO notify) {
        ReportDO reportDO = reportRepository.findById(notify.getReportId()).get();

        BaseModelDO baseModelDO = BaseModelUtils.getModelByReport(reportDO);

        UserDO vioUser = SocialUserUtil.getNotNull(baseModelDO.getUserId());

        PushNotifyVO pushNotifyVO = new PushNotifyVO();
        //构建基础数据
        //操作人
        pushNotifyVO.setNickname(new PushValue(ErrorMsg.serviceName));
        //违规内容
        pushNotifyVO.setBeContent(new PushValue(StringUtils.substring(baseModelDO.getContent(), 0, 20)));
        //违规内容类型
        pushNotifyVO.setBeContentType(new PushValue(reportDO.getReportContentType()));
        //违规用户
        pushNotifyVO.setBeNickname(new PushValue(vioUser.getNickname()));

//        UserDetailDO userDetailDO =  CenterUserUtil.getUserDetail(vioUser.getId());

        //处理结果
//        pushNotifyVO.setResult(new PushValue(StringUtils.substring(userDetailDO.getViolationReason(), 0, 20)));
        //处理时间
        pushNotifyVO.setDate(new PushValue(DateUtils.simpleTimeFormat.format(new Date())));
        //处理备注
        pushNotifyVO.setRemark(new PushValue(StringUtils.substring(ErrorMsg.CONTACT_SERVICE, 0, 20)));

        HashMap<String, Object> data = new HashMap<>();
        PushMsgDTO pushMsgDTO = null;
        if (platform.equals(UniappProviderType.qq)) {
            //违规内容
            data.put("keyword5", pushNotifyVO.getBeContent());
            //违规原因+结果
            data.put("keyword3", pushNotifyVO.getResult());
            //审核时间
            data.put("keyword4", pushNotifyVO.getDate());
            //审核操作人
            data.put("keyword1", pushNotifyVO.getNickname());
            //删除原因
            data.put("keyword2", pushNotifyVO.getRemark());

            pushMsgDTO = new PushMsgDTO(QQConst.violation_template_id, data, "keyword5.DATA");
        } else if (platform.equals(UniappProviderType.wx)) {
            //违规用户名称
            data.put("name1", pushNotifyVO.getBeNickname());
            //违规内容
            data.put("thing2", pushNotifyVO.getBeContent());
            //处罚决定
            data.put("thing3",pushNotifyVO.getResult());
            //备注
            data.put("thing4", pushNotifyVO.getRemark());

            pushMsgDTO = new PushMsgDTO(WxConst.violation_template_id, data);
        }
        return pushMsgDTO;
    }
}
