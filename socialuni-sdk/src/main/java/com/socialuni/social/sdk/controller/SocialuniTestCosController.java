package com.socialuni.social.sdk.controller;

import com.socialuni.social.sdk.dao.DO.user.SocialuniUserSocialCoinDO;
import com.socialuni.social.common.facade.SocialuniRepositoryFacade;
import com.socialuni.social.common.model.ResultRO;
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
        SocialuniUserSocialCoinDO example = new SocialuniUserSocialCoinDO();
        example.setUserId(1);
        SocialuniUserSocialCoinDO socialuniUserSocialCoinDO = SocialuniRepositoryFacade.findByExample(example).get();
        return new ResultRO<>(socialuniUserSocialCoinDO);
    }
}
