package com.socialuni.center.web.url.user;


import com.socialuni.center.web.model.QO.user.SocialUserIdQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.SocialUserDetailRO;
import com.socialuni.center.web.model.QO.user.SocialUserEditQO;
import com.socialuni.center.web.model.QO.user.SocialUserImgAddQO;
import com.socialuni.center.web.model.QO.user.SocialUserImgDeleteQO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("user")
public interface SocialuniUserUrl {

    @PostMapping("getMineUser")
    ResultRO<SocialMineUserDetailRO> getMineUser();

    @PostMapping("queryUserDetail")
    ResultRO<SocialUserDetailRO> queryUserDetail(@RequestBody @Valid SocialUserIdQO socialUserIdQO);

    @PostMapping("editUser")
    ResultRO<SocialMineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO);

    @PostMapping("addUserImg")
    ResultRO<SocialMineUserDetailRO> addUserImg(@RequestBody @Valid SocialUserImgAddQO socialUserImgAddQO);

    @PostMapping("deleteUserImg")
    ResultRO<SocialMineUserDetailRO> deleteUserImg(@RequestBody @Valid SocialUserImgDeleteQO socialUserImgDeleteQO);
}