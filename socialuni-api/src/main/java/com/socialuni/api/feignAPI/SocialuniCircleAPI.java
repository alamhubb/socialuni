package com.socialuni.api.feignAPI;


import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.model.model.RO.community.circle.SocialCircleRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RequestMapping("circle")
@FeignClient(name = "circle", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniCircleAPI {
    @PostMapping("createCircle")
    ResultRO<SocialCircleRO> createCircle(@RequestBody @Valid CircleCreateQO circleCreateQO);

    @PostMapping("queryHotCircles")
    ResultRO<List<SocialCircleRO>> queryHotCircles();
}

