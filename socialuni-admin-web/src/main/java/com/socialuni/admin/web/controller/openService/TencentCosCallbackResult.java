package com.socialuni.admin.web.controller.openService;

import com.socialuni.center.sdk.constant.SocialuniSupportProviderType;
import com.socialuni.center.sdk.model.DevAccountDO;
import com.socialuni.center.sdk.model.DevAccountProviderDO;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
public class TencentCosCallbackResult {
    private Integer code;
    private String message;
    private TencentCosCallbackData data;
}
