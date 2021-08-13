package com.socialuni.center.web.common;

import com.socialuni.social.sdk.exception.SocialBusinessException;
import com.socialuni.social.sdk.platform.tencent.TencentCloud;
import com.socialuni.social.sdk.platform.weixin.HttpResult;
import com.socialuni.social.sdk.utils.WxUtil;
import com.socialuni.social.sdk.service.comment.IllegalWordService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ContentCheck {
    @Resource
    IllegalWordService illegalWordService;

    public void checkShortContent(String content) {
        illegalWordService.checkHasIllegals(content);
        //校验内容是否违规
        if (TencentCloud.textIsViolation(content)) {
            throw new SocialBusinessException("包含内容疑似违规内容，请修改后重试");
        }
        HttpResult wxResult = WxUtil.checkContentWxSec(content);
        if (wxResult.hasError()) {
            throw new SocialBusinessException("包含内容疑似违规内容，请修改后重试");
        }
    }
}
