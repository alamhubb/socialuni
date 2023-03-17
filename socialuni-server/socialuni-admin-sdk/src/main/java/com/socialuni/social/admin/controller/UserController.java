package com.socialuni.social.admin.controller;

import com.socialuni.social.admin.model.ReportRO;
import com.socialuni.social.admin.service.AdminUserService;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.constant.SocialuniUserStatus;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
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
    SocialuniUserRepository userApi;

    @PostMapping("removeUserBanByPhoneNum")
    public ResultRO<List<ReportRO>> removeUserBanByPhoneNum(String phoneNum) {
        SocialuniUserDo user = adminUserService.getUserByPhoneNum(phoneNum);
        if (!user.getStatus().equals(SocialuniUserStatus.violation)) {
            throw new SocialBusinessException("用户未被封禁");
        }
        user.setStatus(SocialuniUserStatus.enable);
        user.setUpdateTime(new Date());
        userApi.savePut(user);
        return ResultRO.success();
    }
}
