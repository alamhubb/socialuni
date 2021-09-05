package com.qingchi.web.controller.oldVersion;

import com.socialuni.api.feignAPI.SocialuniCosAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.utils.JsonUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("cos")
public class OldCosController {
    @Resource
    SocialuniCosAPI socialuniCosAPI;

    @PostMapping("getCosAuthorization")
    public ResultRO<String> getCosAuthorization() {
        ResultRO<SocialCosAuthRO> resultRO = socialuniCosAPI.getCosAuth();
        SocialCosAuthRO socialCosAuthRO = resultRO.getData();
        try {
            String credentialStr = JsonUtil.objectMapper.writeValueAsString(socialCosAuthRO);
            return ResultRO.success(credentialStr);
        } catch (Exception e) {
            //失败抛出异常
            throw new IllegalArgumentException("no valid secret !");
        }
    }
}
