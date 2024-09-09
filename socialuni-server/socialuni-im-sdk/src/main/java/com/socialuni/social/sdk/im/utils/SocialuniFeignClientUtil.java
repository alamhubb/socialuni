/*
package com.socialuni.social.sdk.im.utils;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.api.feign.SocialuniMessageAPI;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignContext;

public class SocialuniFeignClientUtil {


    @Autowired
    private FeignContext feignContext;

    @Value("${servers.list}")
    private List<String> serverList;

    @Autowired
    private SocialuniMessageAPI socialuniMessageAPI;

    // 动态获取不同服务器的 Feign 客户端
    public <T> T getFeignClient(String clientName, Class<T> clientClass, String serverUrl) {
        return feignContext.getInstance(clientName, clientClass, serverUrl);  // 传入具体的客户端类型
    }

    // 遍历调用多个服务器
    public void callMultipleServers(MessageAddVO messageAddVO) {
        for (String serverUrl : serverList) {
            SocialuniMessageAPI client = getFeignClient(serverUrl);

            try {
                ResultRO<SocialMessageRO> result = client.sendMsg(messageAddVO);
                System.out.println("Response from " + serverUrl + ": " + result.getData());
            } catch (Exception e) {
                System.out.println("Error calling " + serverUrl + ": " + e.getMessage());
            }
        }
    }
}
*/
