package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.model.ReportVO;
import com.socialuni.admin.web.service.SocialuniUserService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.repository.UserRepository;
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
    SocialuniUserService socialuniUserService;
    @Resource
    UserRepository userRepository;

    @PostMapping("removeUserBanByPhoneNum")
    public ResultRO<List<ReportVO>> removeUserBanByPhoneNum(String phoneNum) {
        SocialUserDO user = socialuniUserService.getUserByPhoneNum(phoneNum);
        if (!user.getStatus().equals(UserStatus.violation)) {
            throw new SocialBusinessException("用户未被封禁");
        }
        user.setStatus(UserStatus.enable);
        user.setUpdateTime(new Date());
        userRepository.save(user);
        return ResultRO.success();
    }
}
