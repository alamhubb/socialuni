package com.socialuni.demo.socialuni;

import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.QO.user.CenterUserIdQO;
import com.socialuni.api.model.QO.user.CenterUserImgDeleteQO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.demo.model.MineUserDetailRO;
import com.socialuni.demo.utils.UserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialUserEditQO;
import com.socialuni.social.model.model.QO.user.SocialUserIdQO;
import com.socialuni.social.model.model.QO.user.SocialUserImgAddQO;
import com.socialuni.social.model.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.social.sdk.url.user.SocialuniUserUrl;
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
public class UserController  {
    @Resource
    SocialuniUserAPI socialuniUserAPI;

    @PostMapping("getMineUser")
    public ResultRO<MineUserDetailRO> getMineUser() {
        MineUserDetailRO mineUserDetailRO = UserUtil.getMineUser();
        return ResultRO.success(mineUserDetailRO);
    }

    @PostMapping("queryUserDetail")
    public ResultRO<CenterUserDetailRO> queryUserDetail(@RequestBody @Valid CenterUserIdQO centerUserIdQO) {
        return socialuniUserAPI.queryUserDetail(centerUserIdQO);
    }


    @PostMapping("editUser")
    public ResultRO<MineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO) {
        return null;
    }

    @PostMapping("addUserImg")
    public ResultRO<MineUserDetailRO> addUserImg(@RequestBody @Valid SocialUserImgAddQO socialUserImgAddQO) {
        return null;
    }

    @PostMapping("deleteUserImg")
    public ResultRO<MineUserDetailRO> deleteUserImg(@RequestBody @Valid CenterUserImgDeleteQO centerUserImgDeleteQO) {
        return null;
    }
}