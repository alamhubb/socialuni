package com.socialuni.sdk.api;

import com.socialuni.sdk.feignAPI.SocialuniCircleAPI;
import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.sdk.serive.circle.CenterCircleService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@Service
public class SocialuniCircleAPIImpl implements SocialuniCircleAPI {

    @Resource
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
    }
}
