package com.socialuni.demo.controller.socialuni;

import com.socialuni.api.feignAPI.SocialuniFollowAPI;
import com.socialuni.api.feignAPI.SocialuniLocationAPI;
import com.socialuni.api.model.QO.location.LocationQueryQO;
import com.socialuni.api.model.QO.location.LocationQueryRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LocationController implements SocialuniLocationAPI {
    @Resource
    SocialuniLocationAPI socialuniLocationAPI;
    @Override
    public ResultRO<LocationQueryRO> queryLocation(LocationQueryQO queryQO) {
        return socialuniLocationAPI.queryLocation(queryQO);
    }
}
