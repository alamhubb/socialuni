package com.socialuni.social.likee.api;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.likee.model.SocialuniLikeAllConfigBO;
import com.socialuni.social.likee.model.SocialuniLikeChatRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@FeignClient(name = "userLike", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/userLike")
@Tag(name = "用户模块/喜欢用户")
public interface SocialuniUserLikeAPI {
    @PostMapping("likeUser")
    @Operation(summary = "喜欢用户")
    ResultRO<Void> likeUser(@RequestBody @Valid SocialuniUserIdQO addVO);

    @GetMapping("getLikeAllConfig")
    @Operation(summary = "得到喜欢域的所有配置")
    ResultRO<SocialuniLikeAllConfigBO> getLikeAllConfig();

    @PostMapping("sendMsg")
    ResultRO<SocialMessageRO> sendMsg(@RequestBody @Valid MessageAddVO messageAddVO);

    @PostMapping("queryChat")
    ResultRO<SocialuniLikeChatRO> queryChat(@RequestBody @Valid @NotNull SocialuniChatQueryQO socialuniChatQueryQO);

}