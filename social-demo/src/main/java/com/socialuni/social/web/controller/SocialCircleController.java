package com.socialuni.social.web.controller;

import com.socialuni.api.feignAPI.SocialuniCircleAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.model.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.model.model.RO.community.circle.SocialCircleRO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class SocialCircleController implements SocialuniCircleAPI {
    @Override
    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {
        return null;
    }

    @Override
    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        return null;
    }

    @Override
    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        return null;
    }

    /*@Resource
    CenterCircleService centerCircleService;

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
    }*/
}
