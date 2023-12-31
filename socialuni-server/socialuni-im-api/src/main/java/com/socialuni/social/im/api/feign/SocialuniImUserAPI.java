package com.socialuni.social.im.api.feign;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.im.api.model.QO.chat.ChatReadVO;
import com.socialuni.social.im.api.model.QO.chat.ChatRemoveVO;
import com.socialuni.social.im.api.model.QO.chat.OpenChatVO;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.im.api.model.RO.SocialuniImMineUserDetailRO;
import com.socialuni.social.im.api.model.RO.SocialuniImUserDetailRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/chat")
@FeignClient(name = "imUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/imUser")
@Tag(name = "消息模块/会话模块",description = "暂未支持")
public interface SocialuniImUserAPI {
    @PostMapping("getImUserDetail")
    ResultRO<SocialuniImUserDetailRO> getImUserDetail(@RequestBody @Valid SocialuniUserIdQO socialuniUserIdQO);

    @PostMapping("getImMineUser")
    ResultRO<SocialuniImMineUserDetailRO> getImMineUser();

    @PostMapping("closeStrangerMsg")
    ResultRO<SocialuniImMineUserDetailRO> closeStrangerMsg();

    @PostMapping("openStrangerMsg")
    ResultRO<SocialuniImMineUserDetailRO> openStrangerMsg();
}

