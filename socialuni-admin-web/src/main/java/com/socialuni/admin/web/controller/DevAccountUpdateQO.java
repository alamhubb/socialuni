package com.socialuni.admin.web.controller;

import com.socialuni.center.sdk.constant.SocialuniSupportProviderType;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.entity.model.DevAccountProviderDO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class DevAccountUpdateQO {
    @NotBlank
    private String type;
    @NotBlank
    private String realName;

    private String wxMpAppName;
    private String wxMpAppId;
    private String qqMpAppName;
    private String qqMpAppId;
}
