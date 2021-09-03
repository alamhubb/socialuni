package com.socialuni.admin.web.controller;

import com.socialuni.center.sdk.constant.SocialuniSupportProviderType;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.entity.model.DevAccountProviderDO;
import com.socialuni.social.sdk.constant.platform.ProviderType;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
public class DevUserRO {
    private Long id;
    private String secretKey;
    private String token;
    private Long devNum;
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


    public DevUserRO(DevAccountDO devAccountDO) {
        this.devNum = devAccountDO.getDevNum();
        String realPhoneNum = devAccountDO.getPhoneNum();

        this.type = devAccountDO.getType();
        this.realName = devAccountDO.getRealName();

        if (StringUtils.isNotEmpty(realPhoneNum)) {
            this.phoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
        }
        this.secretKey = devAccountDO.getSecretKey().substring(0, 5) + "*****************";

        for (String supportProviderType : SocialuniSupportProviderType.supportProviderTypes) {
            if (SocialuniSupportProviderType.wx.equals(supportProviderType)){
                DevAccountProviderDO wxDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDO(devAccountDO.getId(), supportProviderType);
                if (wxDevAccountProviderDO != null) {
                    this.wxMpAppId = wxDevAccountProviderDO.getAppId();
                    this.wxMpAppName = wxDevAccountProviderDO.getAppName();
                }
            }else if (SocialuniSupportProviderType.qq.equals(supportProviderType)){
                DevAccountProviderDO wxDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDO(devAccountDO.getId(), supportProviderType);
                if (wxDevAccountProviderDO != null) {
                    this.qqMpAppId = wxDevAccountProviderDO.getAppId();
                    this.qqMpAppName = wxDevAccountProviderDO.getAppName();
                }
            }

        }
    }
}
