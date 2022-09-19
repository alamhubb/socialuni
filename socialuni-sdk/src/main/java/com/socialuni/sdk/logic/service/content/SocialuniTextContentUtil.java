package com.socialuni.sdk.logic.service.content;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.ErrorMsg;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.dao.DO.base.SocialuniTextCheckDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.logic.platform.weixin.HttpResult;
import com.socialuni.sdk.logic.service.comment.IllegalWordService;
import com.socialuni.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.sdk.utils.QQUtil;
import com.socialuni.sdk.utils.SocialuniImgContentUtil;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;
import com.socialuni.sdk.utils.content.TextContentUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class SocialuniTextContentUtil {
    private static IllegalWordService illegalWordService;

    @Resource
    public void setIllegalWordService(IllegalWordService illegalWordService) {
        SocialuniTextContentUtil.illegalWordService = illegalWordService;
    }

    //校验名字，圈子名，标签名，用户名，和用户发表的长文本用同一种规则会不会有问题，
    //什么样的内容走预审核msg, 三种，预审核、 禁止发布、 图片校验
    //评论、消息、talk，走 预审核和禁止发布
    //用户名称、tag名称，走禁止发布，因为没有审核机制、
    //有禁止使用的qq号，有微信安全校验，有qq安全校验，可以都走一遍，也不收钱
    //图片校验微信、qq、都有也不花钱，还有内容安全
    //什么样的调用什么样的接口
    //校验用户发表长文本内容，用户评论，用户动态，目前采用的统一规则，短文本是否需要校验年龄不确认，先校验这
    public static void checkTextHasUnderageAndContactAndViolateWords(String content) {
        //不为空才进行校验
        if (StringUtils.isNotEmpty(content)) {
            Boolean disableUnderageContent = SocialuniAppConfig.getAppConfig().getDisableUnderageContent();
            //如果禁止包含未成年人内容
            if (disableUnderageContent) {
                if (SocialuniTextContentUtil.textHasUn18Content(content)) {
                    throw new SocialBusinessException("根据平台规则，禁止发布包含小于18岁未成年的内容");
                }
            }
            //是否禁止包含联系方式
            Boolean disableContentHasContactInfo = SocialuniAppConfig.getAppConfig().getDisableContentHasContactInfo();
            if (disableContentHasContactInfo) {
                if (SocialuniTextContentUtil.hasContactInfo(content)) {
                    throw new SocialBusinessException("禁止发布包含联系方式的内容，可在个人信息中填写联系方式");
                }
            }
        }
        //使用校验短文本内容
        SocialuniTextContentUtil.checkTextHasViolateWords(content);
    }

    //短文本内容中不校验年龄相关
    private static void checkTextHasViolateWords(String content) {
        //不为空才进行校验
        if (StringUtils.isNotEmpty(content)) {
            //校验是否包含违禁词
            illegalWordService.checkHasIllegals(content);
            //只用微信校验一下基本的
            /*HttpResult wxResult = WxUtil.checkTextWxSec(content);
            if (wxResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }*/
            //qq要求必须接入，只用qq校验一下基本的
            HttpResult qqResult = QQUtil.checkContentQQSec(content);
            if (qqResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
        }
    }


    //包含未成年内容
    public static boolean textHasUn18Content(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
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
            if (BirthdayAgeUtil.lessThan18Str(numStr)) {
                return true;
            }
        }
        return false;
    }


    public static boolean hasContactInfo(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        return SocialuniTextContentUtil.hasWxContactInfo(content) || SocialuniTextContentUtil.hasQQContactInfo(content);
    }

    //包含微信联系方式
    private static boolean hasWxContactInfo(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        //去除特殊和空白字符
        content = TextContentUtil.clearAllEmptyAndSpecialChart(content);
        String reg = "\\w{6,20}";//定义正则表达式
        //编译正则表达式
        Pattern patten = Pattern.compile(reg);
        // 指定要匹配的字符串
        Matcher matcher = patten.matcher(content);
        //此处find()每次被调用后，会偏移到下一个匹配
        return matcher.find();
    }

    //包含qq联系方式
    private static boolean hasQQContactInfo(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        //去除特殊和空白字符
        content = TextContentUtil.clearAllEmptyAndSpecialChart(content);
        String reg = "\\d{5,11}";//定义正则表达式
        //编译正则表达式
        Pattern patten = Pattern.compile(reg);
        // 指定要匹配的字符串
        Matcher matcher = patten.matcher(content);
        //此处find()每次被调用后，会偏移到下一个匹配
        return matcher.find();
    }

    public static void validateImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {
        String imgTextContent = SocialuniImgContentUtil.getImgTextContent(socialUserImgAddQO.getSrc());
        socialUserImgAddQO.setContent(imgTextContent);
        boolean hasQrCode = SocialuniImgContentUtil.hasQrCodeByCloudAPI(socialUserImgAddQO.getSrc());
        socialUserImgAddQO.setHasQrCode(hasQrCode);
        //系统管理员不校验相关内容
        if (!UserType.system.equals(mineUser.getType())) {
            // 校验本文规则
            SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(imgTextContent);
            //是否禁止包含联系方式
            Boolean disableContentHasQrCode = SocialuniAppConfig.getAppConfig().getDisableContentHasQrCode();
            if (disableContentHasQrCode) {
                if (hasQrCode) {
                    throw new SocialBusinessException("禁止发布包含二维码的内容，可在个人信息中填写联系方式");
                }
            }
        }
    }

    public static void setHasUnderageAndContactInfoByContentText(String content, SocialuniTextCheckDO socialuniTextCheckDO) {
        //只有当前不包含才校验，已包含则无需再次校验
        if (!socialuniTextCheckDO.getHasUnderageContent()) {
            //包含未成年内容
            if (SocialuniTextContentUtil.textHasUn18Content(content)) {
                socialuniTextCheckDO.setHasUnderageContent(true);
            }
        }
        //只有当前不包含才校验，已包含则无需再次校验
        if (!socialuniTextCheckDO.getHasContactInfo()) {
            //是否包含联系方式
            if (SocialuniTextContentUtil.hasContactInfo(content)) {
                socialuniTextCheckDO.setHasContactInfo(true);
            }
        }
    }
}
