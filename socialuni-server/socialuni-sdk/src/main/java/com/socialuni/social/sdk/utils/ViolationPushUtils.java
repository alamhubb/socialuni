package com.socialuni.social.sdk.utils;

import com.socialuni.social.common.api.constant.SocialSystemConst;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.common.api.constant.SocialuniSupportProviderType;
import com.socialuni.social.report.sdk.entity.ReportDO;
import com.socialuni.social.report.sdk.dao.repository.ReportRepository;
import com.socialuni.social.common.sdk.constant.ErrorMsg;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.report.sdk.utils.SocialuniContentDOUtil;
import com.socialuni.social.common.sdk.platform.qq.QQConst;
import com.socialuni.social.common.sdk.platform.weixin.WxConst;
import com.socialuni.social.common.sdk.platform.PushMsgDTO;
import com.socialuni.social.sdk.im.notify.PushNotifyVO;
import com.socialuni.social.sdk.im.notify.PushValue;
import com.socialuni.social.tance.dev.facade.ConfigFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
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
    private static ReportRepository reportApi;

    @Resource
    public void setReportRepository(ReportRepository reportApi) {
        ViolationPushUtils.reportApi = reportApi;
    }

    //动态评论通知
    public static PushMsgDTO getViolationPushDTO(String platform, NotifyDO notify) {
        ReportDO ReportDO = null;
//        ReportDO ReportDO = reportApi.findById(notify.getContentId()).get();

        SocialuniUnionContentBaseDO baseModelDO = SocialuniContentDOUtil.getContentDOByContentId(ReportDO.getContentId());

        SocialuniUserDo vioUser = SocialuniUserUtil.getUserNotNull(baseModelDO.getUserId());

        PushNotifyVO pushNotifyVO = new PushNotifyVO();
        //构建基础数据
        //操作人
        pushNotifyVO.setNickname(new PushValue(ErrorMsg.serviceName));
        //违规内容
        pushNotifyVO.setBeContent(new PushValue(StringUtils.substring(baseModelDO.getContent(), 0, 20)));
        //违规内容类型
        pushNotifyVO.setBeContentType(new PushValue(ReportDO.getContentType()));
        //违规用户
        pushNotifyVO.setBeNickname(new PushValue(vioUser.getNickname()));

//        UserDetailDO userDetailDO =  SocialuniUserUtil.getUserDetail(vioUser.getId());

        //处理结果
//        pushNotifyVO.setResult(new PushValue(StringUtils.substring(userDetailDO.getViolationReason(), 0, 20)));
        //处理时间
        pushNotifyVO.setDate(new PushValue(SocialuniDateUtils.simpleTimeFormat.format(new Date())));
        //处理备注
        pushNotifyVO.setRemark(new PushValue(StringUtils.substring(ConfigFacade.getFormat(ErrorMsg.CONTACT_SERVICE, SocialSystemConst.CONFIGS_KEY_QQ_ACCOUNT), 0, 20)));

        HashMap<String, Object> data = new HashMap<>();
        PushMsgDTO pushMsgDTO = null;
        if (platform.equals(SocialuniSupportProviderType.qq)) {
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
        } else if (platform.equals(SocialuniSupportProviderType.wx)) {
            //违规用户名称
            data.put("name1", pushNotifyVO.getBeNickname());
            //违规内容
            data.put("thing2", pushNotifyVO.getBeContent());
            //处罚决定
            data.put("thing3", pushNotifyVO.getResult());
            //备注
            data.put("thing4", pushNotifyVO.getRemark());

            pushMsgDTO = new PushMsgDTO(WxConst.violation_template_id, data);
        }
        return pushMsgDTO;
    }
}
