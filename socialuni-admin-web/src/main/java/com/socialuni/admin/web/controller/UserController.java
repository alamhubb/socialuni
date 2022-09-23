package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.model.ReportRO;
import com.socialuni.admin.web.service.AdminUserService;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.dao.repository.UserRepository;
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
    UserRepository userRepository;

    @PostMapping("removeUserBanByPhoneNum")
    public ResultRO<List<ReportRO>> removeUserBanByPhoneNum(String phoneNum) {
        SocialuniUserDO user = adminUserService.getUserByPhoneNum(phoneNum);
        if (!user.getStatus().equals(UserStatus.violation)) {
            throw new SocialBusinessException("用户未被封禁");
        }
        user.setStatus(UserStatus.enable);
        user.setUpdateTime(new Date());
        userRepository.save(user);
        return ResultRO.success();
    }
}
