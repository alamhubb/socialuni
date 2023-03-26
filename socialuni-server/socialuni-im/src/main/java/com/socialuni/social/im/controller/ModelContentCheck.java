package com.socialuni.social.im.controller;

import com.qingchi.base.common.ResultVO;
import com.qingchi.base.constant.ErrorMsg;
import com.qingchi.base.constant.status.UserStatus;
import com.qingchi.base.model.user.UserDO;
import com.qingchi.base.platform.qq.QQUtil;
import com.qingchi.base.platform.weixin.HttpResult;
import com.qingchi.base.platform.weixin.WxUtil;
import com.qingchi.base.service.IllegalWordService;
import com.qingchi.base.utils.QingLogger;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ModelContentCheck {

    @Resource
    private IllegalWordService illegalWordService;

    public ResultVO checkUser(UserDO requestUser) {
        //如果不为系统管理员，只有管理员才能评论置顶内容
        if (StringUtils.isEmpty(requestUser.getPhoneNum())) {
            QingLogger.logger.error("用户未绑定手机号还能调用后台发布功能，用户Id：{}", requestUser.getId());
            return new ResultVO<>(ErrorMsg.bindPhoneNumCan);
        }
        //如果用户状态不为可用
        if (!UserStatus.enable.equals(requestUser.getStatus())) {
            return new ResultVO<>(ErrorMsg.userMaybeViolation);
        }
        return new ResultVO();
    }

    public ResultVO checkContent(String content) {
        //不为空才进行校验
        if (StringUtils.isNotEmpty(content)) {
            ResultVO resultVO = illegalWordService.checkHasIllegals(content);
            //校验内容是否违规
            if (resultVO.hasError()) {
                return new ResultVO<>(resultVO);
            }
            HttpResult wxResult = WxUtil.checkContentWxSec(content);
            if (wxResult.hasError()) {
                return new ResultVO<>(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
            HttpResult qqResult = QQUtil.checkContentQQSec(content);
            if (qqResult.hasError()) {
                return new ResultVO<>(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
        }
        return new ResultVO();
    }

    public ResultVO checkUserAndContent(String content, UserDO requestUser) {
        //如果不为系统管理员，只有管理员才能评论置顶内容
//        if (!UserType.system.equals(requestUser.getType())) {
        ResultVO resultVO = this.checkUser(requestUser);
        if (resultVO.hasError()) {
            return resultVO;
        }
        resultVO = this.checkContent(content);
        if (resultVO.hasError()) {
            return resultVO;
        }
        return new ResultVO();
    }
}
