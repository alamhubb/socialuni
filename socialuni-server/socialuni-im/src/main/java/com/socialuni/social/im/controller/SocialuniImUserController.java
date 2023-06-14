package com.socialuni.social.im.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.im.api.feign.SocialuniChatAPI;
import com.socialuni.social.im.api.feign.SocialuniImUserAPI;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.im.api.model.QO.chat.ChatReadVO;
import com.socialuni.social.im.api.model.QO.chat.ChatRemoveVO;
import com.socialuni.social.im.api.model.QO.chat.OpenChatVO;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.im.api.model.RO.SocialuniImMineUserDetailRO;
import com.socialuni.social.im.api.model.RO.SocialuniImUserDetailRO;
import com.socialuni.social.im.logic.service.SocialuniImUserService;
import com.socialuni.social.im.logic.service.chat.ChatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("socialuni/imUser")
public class SocialuniImUserController implements SocialuniImUserAPI {

    @Resource
    SocialuniImUserService socialuniImUserService;

    @Override
    public ResultRO<SocialuniImUserDetailRO> getImUserDetail(SocialuniUserIdQO socialuniUserIdQO) {
        return socialuniImUserService.getImUserDetail(socialuniUserIdQO);
    }

    @Override
    public ResultRO<SocialuniImMineUserDetailRO> getImMineUser() {
        return socialuniImUserService.getImMineUser();
    }

    @Override
    public ResultRO<SocialuniImMineUserDetailRO> closeStrangerMsg() {
        return socialuniImUserService.closeStrangerMsg();
    }

    @Override
    public ResultRO<SocialuniImMineUserDetailRO> openStrangerMsg() {
        return socialuniImUserService.openStrangerMsg();
    }
}
