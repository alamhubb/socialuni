package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.repository.UserRepository;
import com.socialuni.social.api.model.ResultRO;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("phone")
public class PhoneController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
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
    public ResultRO<String> sendAuthCode(@Valid @NotBlank @Length(min = 11, max = 11) String phoneNum, HttpServletRequest request) {
        return devAuthCodeService.sendAuthCodeHandle(phoneNum, null, request);
    }
}
