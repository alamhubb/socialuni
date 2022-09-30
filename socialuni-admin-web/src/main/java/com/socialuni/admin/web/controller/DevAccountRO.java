package com.socialuni.admin.web.controller;

import com.socialuni.social.sdk.constant.platform.SocialuniSupportProviderType;
import com.socialuni.social.tance.util.DevAccountUtils;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.entity.DevAccountProviderDO;
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


    public DevAccountRO(DevAccountModel devAccountModel) {
        this.devNum = devAccountModel.getDevNum();

        this.type = devAccountModel.getType();
        this.appName = devAccountModel.getAppName();
        this.realName = devAccountModel.getRealName();

        String phoneNum = devAccountModel.getPhoneNum();
        this.phoneNum = phoneNum.substring(0, 3) + "*****" + phoneNum.substring(8);

        if (StringUtils.isNotEmpty(devAccountModel.getSecretKey())) {
            this.secretKey = devAccountModel.getSecretKey().substring(0, 5) + "*****************";
        }

        for (String supportProviderType : SocialuniSupportProviderType.supportProviderTypes) {
            DevAccountProviderDO wxDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByDevAndMpType(devAccountModel.getId(), supportProviderType);
            if (wxDevAccountProviderDO != null) {
                if (SocialuniSupportProviderType.wx.equals(supportProviderType)) {
                    this.wxMpAppId = wxDevAccountProviderDO.getAppId();
                    this.wxMpAppName = wxDevAccountProviderDO.getAppName();
                } else if (SocialuniSupportProviderType.qq.equals(supportProviderType)) {
                    this.qqMpAppId = wxDevAccountProviderDO.getAppId();
                    this.qqMpAppName = wxDevAccountProviderDO.getAppName();
                }
            }
        }
    }
}
