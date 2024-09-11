package com.socialuni.social.likee.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.likee.api.SocialuniUserLikeAPI;
import com.socialuni.social.likee.config.SocialuniLikeAllConfig;
import com.socialuni.social.likee.logic.service.SocialuniUserLikeService;
import com.socialuni.social.likee.model.SocialuniLikeAllConfigBO;
import com.socialuni.social.likee.model.SocialuniLikeChatRO;
import com.socialuni.social.sdk.im.logic.service.SocialuniMessageService;
import com.socialuni.social.sdk.im.logic.service.chat.ChatService;
import com.socialuni.social.tance.dev.api.SocialuniNoUseFeignAspect;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/userLike")
class SocialuniUserLikeControll implements SocialuniUserLikeAPI {
    @Resource
    SocialuniUserLikeService socialuniUserLikeService;


    @SocialuniNoUseFeignAspect
    @Override
    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO messageAddVO) {
        return socialuniUserLikeService.sendMsg(messageAddVO);
    }

    @Override
    public ResultRO<SocialuniLikeChatRO> queryChat(SocialuniChatQueryQO socialuniChatQueryQO) {
        return socialuniUserLikeService.queryChat(socialuniChatQueryQO);
    }


    @SocialuniNoUseFeignAspect
    @Override
    public ResultRO<List<SocialuniLikeChatRO>> queryChatList() {
        return socialuniUserLikeService.queryChatList();
    }

    public ResultRO<Void> likeUser(SocialuniUserIdQO addVO) {
        socialuniUserLikeService.likeUser(addVO);
        return ResultRO.success();
    }

    @Override
    public ResultRO<SocialuniLikeAllConfigBO> getLikeAllConfig() {
        return ResultRO.success(SocialuniLikeAllConfig.getLikeAllConfigBO());
    }
}
