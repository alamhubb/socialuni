package com.socialuni.sdk.controller;

import com.socialuni.sdk.dao.DO.user.SocialuniUserExpandDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserSocialCoinDO;
import com.socialuni.sdk.dao.repository.user.SocialuniUserExpandRepository;
import com.socialuni.sdk.dao.repository.user.SocialuniUserSocialCoinRepository;
import com.socialuni.sdk.dao.utils.common.SocialuniCommonDOUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("test")
public class SocialuniTestCosController {

    @GetMapping("test1")
    public ResultRO test1() {
        SocialuniUserSocialCoinDO socialuniUserSocialCoinDO = new SocialuniUserSocialCoinDO();
        socialuniUserSocialCoinDO.setUserId(1);
        socialuniUserSocialCoinDO.setSocialCoin(0);
        socialuniUserSocialCoinDO = SocialuniCommonDOUtil.save(socialuniUserSocialCoinDO);


        SocialuniUserExpandDO socialuniUserExpandDO = new SocialuniUserExpandDO();
        socialuniUserExpandDO.setUserId(1);
        socialuniUserExpandDO.setOpenContactInfo(false);
        socialuniUserExpandDO = SocialuniCommonDOUtil.save(socialuniUserExpandDO);
        return ResultRO.success(socialuniUserSocialCoinDO);
    }

    @Resource
    SocialuniUserExpandRepository socialuniUserExpandRepository;

    @Resource
    SocialuniUserSocialCoinRepository socialuniUserSocialCoinRepository;

    @GetMapping("test2")
    public ResultRO test2() {
        SocialuniUserSocialCoinDO socialuniUserSocialCoinDO = new SocialuniUserSocialCoinDO();
        socialuniUserSocialCoinDO.setUserId(1);
        socialuniUserSocialCoinDO.setSocialCoin(0);
        socialuniUserSocialCoinDO = SocialuniCommonDOUtil.save(socialuniUserSocialCoinDO);

        socialuniUserSocialCoinRepository.save(socialuniUserSocialCoinDO);


        SocialuniUserExpandDO socialuniUserExpandDO = new SocialuniUserExpandDO();
        socialuniUserExpandDO.setUserId(1);
        socialuniUserExpandDO.setOpenContactInfo(false);
        socialuniUserExpandRepository.save(socialuniUserExpandDO);

        return ResultRO.success(socialuniUserSocialCoinDO);
    }
}
