package com.socialuni.social.common.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.feignAPI.community.SocialuniCircleAPI;
import com.socialuni.social.community.sdk.model.SocialuniTalkTabCircleRO;
import com.socialuni.social.common.sdk.logic.service.circle.SocialuniCircleService;
import com.socialuni.social.common.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.social.common.sdk.model.QO.community.circle.CircleChatCreateQO;
import com.socialuni.social.common.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.common.sdk.model.QO.community.circle.CircleTalkTabInfoQO;
import com.socialuni.social.common.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.community.sdk.model.SocialCircleRO;
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
    public ResultRO<String> createCircleChat(CircleChatCreateQO circleChatCreateQO) {
        return centerCircleService.createCircleChat(circleChatCreateQO);
    }

    @Override
    public ResultRO<SocialuniTalkTabCircleRO> queryCircleTalkTabInfo(CircleTalkTabInfoQO circleTalkTabInfoQO) {
        return centerCircleService.queryCircleTalkTabInfo(circleTalkTabInfoQO);
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
