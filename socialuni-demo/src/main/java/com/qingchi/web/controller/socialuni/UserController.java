package com.qingchi.web.controller.socialuni;

import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.qingchi.web.model.RO.user.UserDetailRO;
import com.qingchi.web.service.UserService;
import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.QO.user.CenterUserIdQO;
import com.socialuni.api.model.QO.user.CenterUserImgDeleteQO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialUserEditQO;
import com.socialuni.social.model.model.QO.user.SocialUserImgAddQO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 查询用户详情
 *
 * @author qinkaiyuan
 * @since 1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("getMineUser")
    public ResultRO<MineUserDetailRO> getMineUser() {
        ResultRO<MineUserDetailRO> mineUserDetailRO = userService.getMineUser();
        return mineUserDetailRO;
    }

    @PostMapping("queryUserDetail")
    public ResultRO<UserDetailRO> queryUserDetail(@RequestBody @Valid CenterUserIdQO centerUserIdQO) {
        ResultRO<UserDetailRO> centerUserDetailROResultRO = userService.queryUserDetail(centerUserIdQO);
        return centerUserDetailROResultRO;
    }

    @PostMapping("editUser")
    public ResultRO<MineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO) {
        return userService.editUser(socialUserEditQO);
    }

    @PostMapping("addUserImg")
    public ResultRO<MineUserDetailRO> addUserImg(@RequestBody @Valid SocialUserImgAddQO socialUserImgAddQO) {
        return userService.addUserImg(socialUserImgAddQO);
    }

    @PostMapping("deleteUserImg")
    public ResultRO<MineUserDetailRO> deleteUserImg(@RequestBody @Valid CenterUserImgDeleteQO centerUserImgDeleteQO) {
        return userService.deleteUserImg(centerUserImgDeleteQO);
    }
}