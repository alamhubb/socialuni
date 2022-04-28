package com.socialuni.social.web.controller;

import com.socialuni.api.feignAPI.SocialuniCircleAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.model.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.model.model.RO.community.circle.SocialCircleRO;
import com.socialuni.social.sdk.service.circle.SocialCircleService;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class SocialCircleController implements SocialuniCircleAPI {
    @Resource
    SocialCircleService socialCircleService;

    @Override
    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {
        UserDO userDO = SocialUserUtil.getMineUserNotNull();
        return socialCircleService.createCircle(circleCreateQO, userDO);
    }

    @Override
    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        return socialCircleService.queryHotCircles();
    }

    @Override
    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        return socialCircleService.queryCircleTypes();
    }
}
