package com.socialuni.social.admin.controller;

import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.model.DevAccountProviderModler;
import com.socialuni.social.common.api.constant.SocialuniSupportProviderType;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
public class DevAccountRO {
    private Long id;
    private String secretKey;
    private String token;
    private Long devNum;
    //通用名称，创建应用专属tag时使用
    private String appName;
    private String phoneNum;
    private String type;
    private String realName;
    private String identityNum;
    private Integer callApiCount;

    private String status;
    private Date createTime;
    private Date updateTime;

    private String wxMpAppName;
    private String wxMpAppId;
    private String qqMpAppName;
    private String qqMpAppId;


    public DevAccountRO(DevAccountDo devAccountDo) {
        this.devNum = devAccountDo.getDevNum();

        this.type = devAccountDo.getType();
        this.appName = devAccountDo.getAppName();
        this.realName = devAccountDo.getRealName();

        String phoneNum = devAccountDo.getPhoneNum();
        if (StringUtils.isNotEmpty(phoneNum)) {
            this.phoneNum = phoneNum.substring(0, 3) + "*****" + phoneNum.substring(8);
        }

        if (StringUtils.isNotEmpty(devAccountDo.getSecretKey())) {
            this.secretKey = devAccountDo.getSecretKey().substring(0, 5) + "*****************";
        }

        for (String supportProviderType : SocialuniSupportProviderType.supportProviderTypes) {
            DevAccountProviderModler wxDevAccountProviderModler = DevAccountFacade.getDevAccountProviderDOByDevAndMpType(devAccountDo.getId(), supportProviderType);
            if (wxDevAccountProviderModler != null) {
                if (SocialuniSupportProviderType.wx.equals(supportProviderType)) {
                    this.wxMpAppId = wxDevAccountProviderModler.getAppId();
                    this.wxMpAppName = wxDevAccountProviderModler.getAppName();
                } else if (SocialuniSupportProviderType.qq.equals(supportProviderType)) {
                    this.qqMpAppId = wxDevAccountProviderModler.getAppId();
                    this.qqMpAppName = wxDevAccountProviderModler.getAppName();
                }
            }
        }
    }
}
