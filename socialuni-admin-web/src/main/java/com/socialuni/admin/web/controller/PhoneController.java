package com.socialuni.admin.web.controller;

import com.qingchi.base.common.ResultVO;
import com.qingchi.base.model.user.UserDO;
import com.qingchi.base.repository.user.UserRepository;
import com.qingchi.base.service.DevAuthCodeService;
import com.qingchi.base.service.LoginService;
import com.qingchi.base.service.UserService;
import com.qingchi.base.utils.IntegerUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("phone")
public class PhoneController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private LoginService loginService;
    @Resource
    private UserService userService;
    @Resource
    private UserRepository userRepository;
    @Resource
    private DevAuthCodeService devAuthCodeService;

    /**
     * 腾讯云手机验证码相关，手机号登陆和绑定都使用这里发送验证码
     *
     * @param phoneNum
     * @return
     * @throws Exception
     */
    @PostMapping("sendAuthCode")
    @ResponseBody
    public ResultVO<String> sendAuthCode(@Valid @NotBlank @Length(min = 11, max = 11) String phoneNum, HttpServletRequest request) {
        return devAuthCodeService.sendAuthCodeHandle(phoneNum, null, request);
    }
}
