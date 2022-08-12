package com.socialuni.web.controller.socialuni;

import com.socialuni.sdk.api.SocialuniCircleAPIImpl;
import com.socialuni.sdk.feignAPI.SocialuniCircleAPI;
import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class CenterCircleController implements SocialuniCircleAPI {

    @Resource
    SocialuniCircleAPIImpl centerCircleAPIImpl;

    @Override
    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {
        return centerCircleAPIImpl.createCircle(circleCreateQO);
    }

    @Override
    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        return centerCircleAPIImpl.queryHotCircles();
    }

    @Override
    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        return centerCircleAPIImpl.queryCircleTypes();
    }
}
