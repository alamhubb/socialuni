package com.socialuni.social.sdk.controller;

import com.socialuni.social.sdk.dao.DO.user.SocialuniUserSocialCoinDO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.facade.SocialuniUserRepositoryFacede;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("test")
public class SocialuniTestCosController {
    @GetMapping("test1")
    public ResultRO testCos() {
        SocialuniUserSocialCoinDO socialuniUserSocialCoinDO = SocialuniUserRepositoryFacede.findByUserId(1, SocialuniUserSocialCoinDO.class);
        return new ResultRO<>(socialuniUserSocialCoinDO);
    }
}
