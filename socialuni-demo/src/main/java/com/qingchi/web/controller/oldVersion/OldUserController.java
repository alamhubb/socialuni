package com.qingchi.web.controller.oldVersion;

import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.qingchi.web.model.old.UserImgDeleteVO;
import com.qingchi.web.service.PhoneService;
import com.qingchi.web.service.UserService;
import com.socialuni.api.model.QO.user.CenterUserImgDeleteQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
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
public class OldUserController {
    @Resource
    UserService userService;

    @Resource
    PhoneService phoneService;

    @Deprecated
    @PostMapping("bindPhoneNumByWx")
    public ResultRO<MineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        return phoneService.bindWxPhoneNum(bindWxPhoneNumQO);
    }

    @Deprecated
    @PostMapping("mine")
    public ResultRO<MineUserDetailRO> mine() {
        ResultRO<MineUserDetailRO> mineUserDetailRO = userService.getMineUser();
        return mineUserDetailRO;
    }

    @Deprecated
    @PostMapping("edit")
    public ResultRO<MineUserDetailRO> edit(@RequestBody @Valid SocialUserEditQO socialUserEditQO) {
        return userService.editUser(socialUserEditQO);
    }

    @Deprecated
    @PostMapping("addImg")
    public ResultRO<MineUserDetailRO> addUserImg(@RequestBody @Valid SocialUserImgAddQO socialUserImgAddQO) {
        return userService.addUserImg(socialUserImgAddQO);
    }

    @Deprecated
    @PostMapping("deleteImg")
    public ResultRO<MineUserDetailRO> deleteUserImg(@RequestBody @Valid UserImgDeleteVO userImgDeleteVO) {
        CenterUserImgDeleteQO centerUserImgDeleteQO = new CenterUserImgDeleteQO(userImgDeleteVO.getId());
        return userService.deleteUserImg(centerUserImgDeleteQO);
    }
}