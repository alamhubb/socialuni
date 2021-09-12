package com.socialuni.demo.socialuni;

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
public class UserController implements SocialuniUserAPI {
    @Resource
    SocialuniUserAPI socialuniUserAPI;

    @Override
    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        return socialuniUserAPI.getMineUser();
    }

    @Override
    public ResultRO<CenterUserDetailRO> queryUserDetail(CenterUserIdQO centerUserIdQO) {
        return socialuniUserAPI.queryUserDetail(centerUserIdQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        return socialuniUserAPI.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        return socialuniUserAPI.addUserImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> deleteUserImg(CenterUserImgDeleteQO centerUserImgDeleteQO) {
        return socialuniUserAPI.deleteUserImg(centerUserImgDeleteQO);
    }
}