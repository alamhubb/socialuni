package com.socialuni.sdk.logic.service.content;

import com.socialuni.sdk.constant.ErrorMsg;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.logic.platform.weixin.HttpResult;
import com.socialuni.sdk.logic.service.comment.IllegalWordService;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.utils.QQUtil;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.WxUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ModelContentCheck {
    public static final HashMap<String, Integer> hanziNumberMap = new HashMap<String, Integer>() {{
        put("一", 1);
        put("二", 2);
        put("三", 3);
        put("四", 4);
        put("五", 5);
        put("六", 6);
        put("七", 7);
        put("八", 8);
        put("九", 9);
        put("十", 1);
        put("壹", 1);
        put("贰", 2);
        put("叁", 3);
        put("肆", 4);
        put("伍", 5);
        put("陆", 6);
        put("柒", 7);
        put("捌", 8);
        put("玖", 9);
        put("拾", 1);
    }};


    @Resource
    private IllegalWordService illegalWordService;

    public void checkUser(SocialuniUserDO mineUser) {
        SocialUserPhoneDO userPhoneNum = SocialuniUserUtil.getUserPhoneNumDO(mineUser.getUnionId());

        //如果不为系统管理员，只有管理员才能评论置顶内容
        //未绑定手机号，不能发表动态，正常用户应该无法访问，应为突破了内容，或者逻辑有问题
        if (userPhoneNum == null) {
            throw new SocialParamsException(ErrorMsg.bindPhoneNumCan);
        }
        //如果用户状态不为可用
        if (!UserStatus.enable.equals(mineUser.getStatus())) {
            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
        }
    }

    public void checkContent(String content) {
        //是否包含小于18岁的内容
        //不为空才进行校验
        if (StringUtils.isNotEmpty(content)) {
            //校验内容是否违规
            illegalWordService.checkHasIllegals(content);
            /*HttpResult wxResult = WxUtil.checkContentWxSec(content);
            if (wxResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
            HttpResult qqResult = QQUtil.checkContentQQSec(content);
            if (qqResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }*/
        }
    }

    public static void main(String[] args) {
        ModelContentCheck.hasUn18Content("有没有十5 以下的妹妹 我qq2973327997");
    }

    //包含未成年内容
    public static void hasUn18Content(String content) {
        content = BirthdayAgeUtil.formatHanziNumContent(content);

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

    public void checkUserAndContent(String content, SocialuniUserDO requestUser) {
        //如果不为系统管理员，只有管理员才能评论置顶内容
//        if (!UserType.system.equals(requestUser.getType())) {
        this.checkUser(requestUser);
        if (StringUtils.isNotEmpty(content)) {
            boolean hasAuth = SocialuniUserUtil.getUserIsIdentityAuth(requestUser.getUnionId());
            if (!hasAuth) {
                ModelContentCheck.hasUn18Content(content);
            }
            this.checkContent(content);
        }
    }

    public void checkUserAndLongContent(String content, SocialuniUserDO mineUser) {
        this.checkUserAndContent(content, mineUser);
        //不为空才进行校验
        if (StringUtils.isNotEmpty(content)) {
            HttpResult wxResult = WxUtil.checkTextWxSec(content);
            if (wxResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
            HttpResult qqResult = QQUtil.checkContentQQSec(content);
            if (qqResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
        }
    }
}
