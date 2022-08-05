package com.socialuni.center.web.controller;

import com.socialuni.center.web.feignAPI.SocialuniChatAPI;
import com.socialuni.center.web.feignAPI.SocialuniCircleAPI;
import com.socialuni.center.web.serive.circle.CenterCircleService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.DO.circle.SocialCircleDO;
import com.socialuni.center.web.model.QO.community.circle.CircleCreateQO;
import com.socialuni.center.web.model.RO.community.circle.CircleTypeRO;
import com.socialuni.center.web.model.RO.community.circle.SocialCircleRO;
import com.socialuni.center.web.model.RO.message.chat.ChatRO;
import com.socialuni.center.web.model.RO.message.chat.ChatReadVO;
import com.socialuni.center.web.model.RO.message.chat.ChatRemoveVO;
import com.socialuni.center.web.service.SocialChatService;
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
