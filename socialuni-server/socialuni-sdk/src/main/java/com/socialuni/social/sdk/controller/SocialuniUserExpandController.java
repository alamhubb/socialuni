package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.user.SocialuniUserExpandAPI;
import com.socialuni.social.sdk.logic.service.user.SocialuniUserExpandService;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserIdQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserContactInfoEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserSchoolNameEditQO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/userExpand")
public class SocialuniUserExpandController implements SocialuniUserExpandAPI {
    @Resource
    SocialuniUserExpandService socialuniUserExpandService;

    @Override
    public ResultRO<SocialuniMineUserDetailRO> editUserSchool(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        return socialuniUserExpandService.editUserSchoolName(socialMineUserDetailQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> editUserContactInfo(SocialUserContactInfoEditQO socialMineUserDetailQO) {
        return socialuniUserExpandService.editUserContactInfo(socialMineUserDetailQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> switchOpenUserContactInfo(SocialUserContactInfoEditQO socialuniMineUserDetailRO) {
        //切换是否开启联系方式功能，未来可以，默认价格1元
        // 开启 是否允许别人获取自己的联系方式，默认是关闭的。 需要设置了自己的联系方式，然后才可以开启和关闭
        //如果为空，则设置后默认开启


        return null;
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> getUserContactInfo(SocialuniUserIdQO socialuniUserIdQO) {
        //获取对方联系方式
        //然后确认对方是否拥有联系方式。
        //首先需要确认，对方是否开启获取联系方式
        //如果都有则可以获取。然后就是付费逻辑了

        return null;
    }
}
