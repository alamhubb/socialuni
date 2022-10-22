package com.socialuni.social.sdk.controller;

import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinModel;
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
        SocialuniUserSocialCoinModel socialuniUserSocialCoinModel = SocialuniUserRepositoryFacede.findByUserId(1, SocialuniUserSocialCoinModel.class);
        return new ResultRO<>(socialuniUserSocialCoinModel);
    }
}
