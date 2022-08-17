package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.feignAPI.SocialuniFollowAPI;
import com.socialuni.sdk.model.QO.follow.CenterFollowAddQO;
import com.socialuni.sdk.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.sdk.serive.CenterFollowService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class CenterFollowController {
    @Resource
    CenterFollowService centerFollowService;

    @PostMapping("queryUserFollows")
    public ResultRO<Map<String, List<CenterUserFollowDetailRO>>> queryUserFollows() {
        return centerFollowService.queryUserFollows();
    }

    @PostMapping("addFollow")
    public ResultRO<Void> addFollow(@RequestBody @Valid CenterFollowAddQO addVO) {
        return centerFollowService.addFollow(addVO);
    }

    @PostMapping("cancelFollow")
    public ResultRO<Void> cancelFollow(@RequestBody @Valid CenterFollowAddQO addVO) {
        return centerFollowService.cancelFollow(addVO);
    }
}