package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.community.SocialuniCircleAPI;
import com.socialuni.social.sdk.logic.service.circle.SocialuniCircleService;
import com.socialuni.social.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.social.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.sdk.model.RO.community.circle.SocialCircleRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("socialuni/circle")
public class SocialuniCircleController implements SocialuniCircleAPI {

    @Resource
    SocialuniCircleService centerCircleService;


    @Override
    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {
        return centerCircleService.createCircle(circleCreateQO);
    }

    @Override
    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        return centerCircleService.queryHotCircles();
    }

    @Override
    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        return centerCircleService.queryCircleTypes();
    }

    @Override
    public ResultRO<List<CircleTypeRO>> queryHotCircleTypes() {
        return centerCircleService.queryHotCircleTypes();
    }

    @Override
    public ResultRO<List<SocialCircleRO>> queryCirclesByCircleType(SocialuniCircleQueryByTypeQO socialuniCircleQueryByTypeQO) {
        return centerCircleService.queryCirclesByCircleType(socialuniCircleQueryByTypeQO);
    }

}
