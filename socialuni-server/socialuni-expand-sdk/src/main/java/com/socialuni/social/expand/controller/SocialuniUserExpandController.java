package com.socialuni.social.expand.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserExtendDetailRO;
import com.socialuni.social.expand.api.SocialuniUserExpandAPI;
import com.socialuni.social.expand.logic.service.SocialuniUserExpandService;
import com.socialuni.social.expand.model.SocialuniUserExpandDetailRO;
import com.socialuni.social.expand.utils.SocialuniUserExpandUtil;
import com.socialuni.social.user.sdk.model.QO.SocialUserContactInfoEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserSchoolNameEditQO;
import com.socialuni.social.user.sdk.model.QO.user.SocialuniUserExtendFriendQueryQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/userExpand")
public class SocialuniUserExpandController implements SocialuniUserExpandAPI {
    @Resource
    SocialuniUserExpandService socialuniUserExpandService;

    @Override
    public ResultRO<SocialuniUserExpandDetailRO> editUserSchool(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        return socialuniUserExpandService.editUserSchoolName(socialMineUserDetailQO);
    }

    @Override
    public ResultRO<SocialuniUserExpandDetailRO> editUserContactInfo(SocialUserContactInfoEditQO socialMineUserDetailQO) {
        return socialuniUserExpandService.editUserContactInfo(socialMineUserDetailQO);
    }

    @Override
    public ResultRO<SocialuniUserExpandDetailRO> switchOpenUserContactInfo(SocialUserContactInfoEditQO socialuniMineUserDetailRO) {
        //切换是否开启联系方式功能，未来可以，默认价格1元
        // 开启 是否允许别人获取自己的联系方式，默认是关闭的。 需要设置了自己的联系方式，然后才可以开启和关闭
        //如果为空，则设置后默认开启


        return null;
    }

    @Override
    public ResultRO<List<SocialuniUserExtendDetailRO>> queryExtendFriendUsers(SocialuniPageQueryQO<SocialuniUserExtendFriendQueryQO> socialuniPageQueryQO) {
        return socialuniUserExpandService.queryExtendFriendUsers(socialuniPageQueryQO);
    }

    @Override
    public ResultRO<String> getUserContactInfo(String userId) {
        return socialuniUserExpandService.getUserContactInfo(userId);
    }

    @Override
    public ResultRO<SocialuniUserExpandDetailRO> getMineUserExpandDetail() {
        return ResultRO.success(SocialuniUserExpandUtil.getMineUserExpandDetail());
    }
}
