package com.socialuni.social.sdk.service.content;

import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.sdk.exception.SocialBusinessException;
import com.socialuni.social.sdk.exception.SocialParamsException;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.service.comment.IllegalWordService;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class ModelContentCheck {

    @Resource
    private IllegalWordService illegalWordService;

    public void checkUser(UserDO mineUser) {
        String phoneNum = SocialUserUtil.getUserPhoneNum(mineUser.getId());
        //如果不为系统管理员，只有管理员才能评论置顶内容
        if (StringUtils.isEmpty(phoneNum)) {
            log.error("用户未绑定手机号还能调用后台发布功能，用户Id：{}", mineUser.getId());
            throw new SocialParamsException(ErrorMsg.bindPhoneNumCan);
        }
        //如果用户状态不为可用
        if (!UserStatus.enable.equals(mineUser.getStatus())) {
            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
        }
    }

    public void checkContent(String content) {
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

    public void checkUserAndContent(String content, UserDO requestUser) {
        //如果不为系统管理员，只有管理员才能评论置顶内容
//        if (!UserType.system.equals(requestUser.getType())) {
        this.checkUser(requestUser);
        this.checkContent(content);
    }
}
