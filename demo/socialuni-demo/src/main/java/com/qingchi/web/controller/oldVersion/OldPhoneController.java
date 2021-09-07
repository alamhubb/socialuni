package com.qingchi.web.controller.oldVersion;

import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.qingchi.web.service.PhoneService;
import com.socialuni.api.feignAPI.insystem.SocialuniQingchiAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 查询用户详情
 *
 * @author qinkaiyuan
 * @since 1.0.0
 */
@RestController
@RequestMapping("phone")
public class OldPhoneController {
    @Resource
    PhoneService phoneService;

    @Deprecated
    @PostMapping("sendAuthCode")
    public ResultRO<Void> sendAuthCode(@Valid @NotBlank @Length(min = 11, max = 11) String phoneNum) {
        return phoneService.sendAuthCode(new SocialSendAuthCodeQO(phoneNum));
    }

    @PostMapping("bindPhoneNum")
    public ResultRO<MineUserDetailRO> bindPhoneNum(@RequestBody @Valid SocialPhoneNumQO phoneNumQO) {
        return phoneService.bindPhoneNum(phoneNumQO);
    }
}