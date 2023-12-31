package com.socialuni.social.peiwan.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.peiwan.sdk.logic.SocialuniPeiwanService;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("socialuni/peiwan")
@RestController
@Slf4j
public class SocialuniPeiwanController {

    @Resource
    SocialuniPeiwanService socialuniPeiwanService;

    @GetMapping("queryPeiwanInfoList")
    public ResultRO<List<SocialuniPeiwanInfoRO>> queryPeiwanInfoList() {
        return socialuniPeiwanService.queryPeiwanInfoList();
    }

}
