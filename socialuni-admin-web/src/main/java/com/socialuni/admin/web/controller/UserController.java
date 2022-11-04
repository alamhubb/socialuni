package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.model.ReportRO;
import com.socialuni.admin.web.service.AdminUserService;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import com.socialuni.social.user.sdk.repository.UserRepository;
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
    UserRepository userApi;

    @PostMapping("removeUserBanByPhoneNum")
    public ResultRO<List<ReportRO>> removeUserBanByPhoneNum(String phoneNum) {
        SocialuniUserDo user = adminUserService.getUserByPhoneNum(phoneNum);
        if (!user.getStatus().equals(UserStatus.violation)) {
            throw new SocialBusinessException("用户未被封禁");
        }
        user.setStatus(UserStatus.enable);
        user.setUpdateTime(new Date());
        userApi.savePut(user);
        return ResultRO.success();
    }
}
