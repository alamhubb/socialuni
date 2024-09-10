package com.socialuni.social.im.api.feign;


import com.socialuni.social.common.api.model.SocialuniNotifyRO;
import com.socialuni.social.im.api.model.DTO.SocialuniNotifyDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/chat")
@FeignClient(name = "notifyCallback", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/notifyCallback")
@Tag(name = "消息模块/会话模块", description = "暂未支持")
public interface SocialuniCallbackAPI {
    //发送消息本系统写完
    //调用用中心
    //中心判断要发送给哪些用户
    //如果发送给所有用户
    //中心调用遍历
    //调用发送接口
    //子应用接到数据
    //调用websocket，发送信息。
    @PostMapping
    void receiveNotify(URI host, @RequestBody SocialuniNotifyRO notifyDTO);
}

