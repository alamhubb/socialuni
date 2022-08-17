package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.sdk.serive.circle.CenterCircleService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("circle")
public class OldCenterCircleController {

    @Resource
    CenterCircleService centerCircleService;

    @PostMapping("createCircle")
    public ResultRO<SocialCircleRO> createCircle(@RequestBody @Valid CircleCreateQO circleCreateQO) {
        return centerCircleService.createCircle(circleCreateQO);
    }

    @PostMapping("queryHotCircles")
    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        return centerCircleService.queryHotCircles();
    }

    @PostMapping("queryCircleTypes")
    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        return centerCircleService.queryCircleTypes();
    }
}
