package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.repository.UserRepository;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.entity.model.DevTokenDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.utils.PhoneNumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("user")
@Slf4j
public class AdminLoginController {
    @PostMapping("phoneLogin")
    public ResultRO<LoginRO> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO) {
        ResultRO<LoginRO> resultRO = loginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }



}
