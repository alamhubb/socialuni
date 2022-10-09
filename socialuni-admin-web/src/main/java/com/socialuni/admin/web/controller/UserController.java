package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.model.ReportRO;
import com.socialuni.admin.web.service.AdminUserService;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.user.sdk.api.UserApi;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.sdk.constant.status.UserStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    AdminUserService adminUserService;
    @Resource
    UserApi userApi;

    @PostMapping("removeUserBanByPhoneNum")
    public ResultRO<List<ReportRO>> removeUserBanByPhoneNum(String phoneNum) {
        SocialuniUserModel user = adminUserService.getUserByPhoneNum(phoneNum);
        if (!user.getStatus().equals(UserStatus.violation)) {
            throw new SocialBusinessException("用户未被封禁");
        }
        user.setStatus(UserStatus.enable);
        user.setUpdateTime(new Date());
        userApi.save(user);
        return ResultRO.success();
    }
}
