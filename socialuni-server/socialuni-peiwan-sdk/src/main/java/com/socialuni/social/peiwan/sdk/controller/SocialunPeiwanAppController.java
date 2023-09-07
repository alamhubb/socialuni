package com.socialuni.social.peiwan.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.peiwan.sdk.logic.SocialuniPeiwanService;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanAppInitData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("socialuni/peiwan")
@RestController
@Slf4j
public class SocialunPeiwanAppController {

    @GetMapping("getAppInitData")
    public ResultRO<SocialuniPeiwanAppInitData> getAppInitData() {
        return ResultRO.success(new SocialuniPeiwanAppInitData());
    }

}
