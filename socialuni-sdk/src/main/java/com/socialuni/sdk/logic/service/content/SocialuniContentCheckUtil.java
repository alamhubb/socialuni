package com.socialuni.sdk.logic.service.content;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.ErrorMsg;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.sdk.logic.platform.tencent.TencentCloud;
import com.socialuni.sdk.logic.platform.weixin.HttpResult;
import com.socialuni.sdk.logic.service.comment.IllegalWordService;
import com.socialuni.sdk.utils.QQUtil;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.WxUtil;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;
import com.socialuni.sdk.utils.content.TextContentUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class SocialuniContentCheckUtil {
    private static IllegalWordService illegalWordService;

    @Resource
    public void setIllegalWordService(IllegalWordService illegalWordService) {
        SocialuniContentCheckUtil.illegalWordService = illegalWordService;
    }

    //校验名字，圈子名，标签名，用户名，和用户发表的长文本用同一种规则会不会有问题，
    //什么样的内容走预审核msg, 三种，预审核、 禁止发布、 图片校验
    //评论、消息、talk，走 预审核和禁止发布
    //用户名称、tag名称，走禁止发布，因为没有审核机制、
    //有禁止使用的qq号，有微信安全校验，有qq安全校验，可以都走一遍，也不收钱
    //图片校验微信、qq、都有也不花钱，还有内容安全
    //什么样的调用什么样的接口
    //校验用户发表长文本内容，用户评论，用户动态
    public static void checkUserInputLongTextContent(String content, SocialuniUserDO mineUser) {
        //校验用户
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusNoEnable(mineUser);
        //不为空才进行校验
        if (StringUtils.isNotEmpty(content)) {
            //校验是否包含违禁词
            illegalWordService.checkHasIllegals(content);

            HttpResult wxResult = WxUtil.checkTextWxSec(content);
            if (wxResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
            HttpResult qqResult = QQUtil.checkContentQQSec(content);
            if (qqResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
            //获取是否禁用18岁以下未成年人内容。
            Boolean disableUnderageContent = SocialuniAppConfig.getAppConfig().getDisableUnderageContent();
            //如果禁用，则判断是否包含未成年人内容
            if (disableUnderageContent) {
                //本系统内的违规词校验
                SocialuniContentCheckUtil.hasUn18ContentThrowError(content);
            }
        }
    }

    //包含未成年内容
    public static void hasUn18ContentThrowError(String content) {
        //去除特殊和空白字符
        content = TextContentUtil.clearAllEmptyAndSpecialChart(content);
        //将其他字符的数字转为int数字
        content = TextContentUtil.formatOtherNumContent(content);

        String reg = "\\d+";//定义正则表达式
        //编译正则表达式
        Pattern patten = Pattern.compile(reg);
        // 指定要匹配的字符串
        Matcher matcher = patten.matcher(content);
        //此处find()每次被调用后，会偏移到下一个匹配
        while (matcher.find()) {
            //获取当前匹配的值
            String numStr = matcher.group();
            if (BirthdayAgeUtil.ageBetween10to18Str(numStr)) {
                throw new SocialBusinessException("根据平台规则，禁止发布包含小于18岁未成年的内容，规避此规则内容会按违反社区规则进行封号处罚");
            }
        }
    }
}
