package com.socialuni.sdk.utils;

import com.socialuni.sdk.platform.tencent.TencentCloud;
import com.socialuni.sdk.platform.weixin.HttpResult;
import com.socialuni.sdk.logic.service.comment.IllegalWordService;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;

import javax.annotation.Resource;

public class ContentCheckUtil {
    @Resource
    IllegalWordService illegalWordService;

    //什么样的内容走预审核msg, 三种，预审核、 禁止发布、 图片校验
    //评论、消息、talk，走 预审核和禁止发布
    //用户名称、tag名称，走禁止发布，因为没有审核机制、

    //有禁止使用的qq号，有微信安全校验，有qq安全校验，可以都走一遍，也不收钱
    //图片校验微信、qq、都有也不花钱，还有内容安全

    //什么样的调用什么样的接口

    public void checkShortContent(String content) {
        illegalWordService.checkHasIllegals(content);
        //校验内容是否违规
        if (TencentCloud.textIsViolation(content)) {
            throw new SocialBusinessException("包含内容疑似违规内容，请修改后重试");
        }
        HttpResult wxResult = WxUtil.checkTextWxSec(content);
        if (wxResult.hasError()) {
            throw new SocialBusinessException("包含内容疑似违规内容，请修改后重试");
        }
    }
}
