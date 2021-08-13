package com.qingchi.social.web.controller;

import com.qingchi.base.common.ResultVO;
import com.qingchi.base.model.account.DevAccountDO;
import com.qingchi.base.model.user.DevTokenDO;
import com.qingchi.base.modelVO.DevUserAddVO;
import com.qingchi.base.repository.openData.DevAccountRepository;
import com.qingchi.base.repository.user.DevTokenRepository;
import com.qingchi.base.repository.user.UserRepository;
import com.qingchi.base.service.DevAuthCodeService;
import com.qingchi.base.service.LoginService;
import com.qingchi.base.service.UserService;
import com.qingchi.base.service.create.DevAccountCreate;
import com.qingchi.base.utils.IntegerUtils;
import com.qingchi.base.utils.TokenUtils;
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
@RequestMapping("user")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private LoginService loginService;
    @Resource
    private UserService userService;
    @Resource
    private UserRepository userRepository;
    @Resource
    private DevAuthCodeService devAuthCodeService;
    @Resource
    private DevAccountRepository devAccountRepository;
    @Resource
    private DevAccountCreate devAccountCreate;
    @Resource
    private DevTokenRepository devTokenRepository;

    @PostMapping("login")
    public ResultVO<DevUserAddVO> userLogin(@RequestBody LoginUserVO loginVO, Long devId) {
        //所有平台，手机号登陆方式代码一致
        //登录的时候如果没有手机号，则手机号注册成功，自动注册一个user，用户名待填，自动生成一个昵称，密码待填，头像待上传
        //如果已经登录过，则返回那个已经注册的user，根据手机号获取user，返回登录成功
        //记录用户错误日志
        String phoneNum = loginVO.getPhoneNum();
        String authCode = loginVO.getAuthCode();
        //如果存在非数字
        //1.为空 2. 包含非数字 3.不为11位 ，返回
        if (StringUtils.isEmpty(phoneNum) || IntegerUtils.strHasNoNumber(phoneNum) || phoneNum.length() != 11) {
            logger.error("有人跳过前端，直接访问后台，错误手机号");
            return new ResultVO<>("请输入正确的手机号");
        }
        //校验验证码，传null用户记录日志
        ResultVO resultVO = devAuthCodeService.verifyAuthCode(phoneNum, authCode, null);
        if (resultVO.hasError()) {
            return resultVO;
        }
        //如果手机号已经存在账户，则直接使用，正序获取第一个用户
        Optional<DevAccountDO> userDOOptional = devAccountRepository.findFirstByPhoneNumOrderByIdAsc(phoneNum);
        DevAccountDO devAccountDO;
        //有用户返回，没有创建
//        String platform = loginVO.getPlatform();
        devAccountDO = userDOOptional.orElseGet(() -> devAccountCreate.createDevAccount(phoneNum));

        Integer userId = devAccountDO.getId();
        //生成userToken
        String userToken = TokenUtils.generateTokenById(userId, devId);
        userToken = devTokenRepository.save(new DevTokenDO(userToken, userId)).getTokenCode();

        DevUserAddVO devUserAddVO = new DevUserAddVO(devAccountDO);
        devUserAddVO.setToken(userToken);
        //则更新用户手机号
        return new ResultVO<>(devUserAddVO);
    }

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
