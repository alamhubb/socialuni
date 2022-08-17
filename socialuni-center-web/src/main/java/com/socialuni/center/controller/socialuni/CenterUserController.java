package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.model.QO.user.CenterUserIdQO;
import com.socialuni.sdk.model.QO.user.CenterUserImgDeleteQO;
import com.socialuni.sdk.model.QO.user.SocialUserEditQO;
import com.socialuni.sdk.model.QO.user.SocialUserImgAddQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.CenterUserDetailRO;
import com.socialuni.sdk.serive.CenterUserService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class CenterUserController{
    @Resource
    private CenterUserService centerUserAPIImpl;

    @PostMapping("getMineUser")
    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        return centerUserAPIImpl.getMineUser();
    }

    @PostMapping("queryUserDetail")
    public ResultRO<CenterUserDetailRO> queryUserDetail(@RequestBody @Valid CenterUserIdQO centerUserIdQO) {
        return centerUserAPIImpl.queryUserDetail(centerUserIdQO);
    }

    @PostMapping("editUser")
    public ResultRO<CenterMineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO) {
        return centerUserAPIImpl.editUser(socialUserEditQO);
    }

    @PostMapping("addUserImg")
    public ResultRO<CenterMineUserDetailRO> addUserImg(@RequestBody @Valid SocialUserImgAddQO socialUserImgAddQO) {
        return centerUserAPIImpl.addUserImg(socialUserImgAddQO);
    }

    @PostMapping("deleteUserImg")
    public ResultRO<CenterMineUserDetailRO> deleteUserImg(@RequestBody @Valid CenterUserImgDeleteQO centerUserImgDeleteQO){
        return centerUserAPIImpl.deleteUserImg(centerUserImgDeleteQO);
    }
}
