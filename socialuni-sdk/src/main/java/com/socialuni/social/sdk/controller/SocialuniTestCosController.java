package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.sdk.facade.SocialuniUserRepositoryFacede;
import com.socialuni.social.user.sdk.entity.SocialuniUserSocialCoinDo;
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
        SocialuniUserSocialCoinDo SocialuniUserSocialCoinDo = SocialuniUserRepositoryFacede.findByUserId(1, SocialuniUserSocialCoinDo.class);
        return new ResultRO<>(SocialuniUserSocialCoinDo);
    }
}
