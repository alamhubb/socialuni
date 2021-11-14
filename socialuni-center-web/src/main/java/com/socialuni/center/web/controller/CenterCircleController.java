package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniChatAPI;
import com.socialuni.api.feignAPI.SocialuniCircleAPI;
import com.socialuni.center.web.serive.circle.CenterCircleService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.circle.SocialCircleDO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.model.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.model.model.RO.community.circle.SocialCircleRO;
import com.socialuni.social.model.model.RO.message.chat.ChatRO;
import com.socialuni.social.model.model.RO.message.chat.ChatReadVO;
import com.socialuni.social.model.model.RO.message.chat.ChatRemoveVO;
import com.socialuni.social.sdk.service.SocialChatService;
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
