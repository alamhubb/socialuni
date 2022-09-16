package com.socialuni.sdk.controller;

import com.socialuni.sdk.logic.domain.circle.CircleQueryDomain;
import com.socialuni.sdk.feignAPI.SocialuniCircleAPI;
import com.socialuni.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.logic.service.circle.SocialuniCircleService;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class SocialuniCircleController implements SocialuniCircleAPI {

    @Resource
    SocialuniCircleService centerCircleService;
    @Resource
    CircleQueryDomain circleQueryDomain;
    @Resource
    SocialCircleRepository socialCircleRepository;

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
    public ResultRO<List<SocialCircleRO>> queryCirclesByCircleType(SocialuniCircleQueryByTypeQO socialuniCircleQueryByTypeQO) {
        List<SocialCircleRO> list = circleQueryDomain.queryCirclesByCircleType(socialuniCircleQueryByTypeQO);
        return ResultRO.success(list);
    }

}
