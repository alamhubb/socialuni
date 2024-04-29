package com.socialuni.social.userImg.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.userImg.api.SocialuniUserImgAPI;
import com.socialuni.social.userImg.logic.domain.SocialuniUserImgService;
import com.socialuni.social.userImg.model.SocialuniUserImgDeleteQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/userImg")
public class SocialuniUserImgController implements SocialuniUserImgAPI {
    @Resource
    SocialuniUserImgService socialuniUserImgService;

    @Override
    public ResultRO<List<SocialuniUserImgRO>> addUserImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniUserImgService.addUserImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<List<SocialuniUserImgRO>> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        return socialuniUserImgService.deleteUserImg(centerUserImgDeleteQO);
    }

    @Override
    public ResultRO<List<SocialuniUserImgRO>> getUserImgList(String userId) {
        return socialuniUserImgService.getUserImgList(userId);
    }

    @Override
    public ResultRO<List<SocialuniUserImgRO>> getMineUserImgList() {
        return socialuniUserImgService.getMineUserImgList();
    }
}
