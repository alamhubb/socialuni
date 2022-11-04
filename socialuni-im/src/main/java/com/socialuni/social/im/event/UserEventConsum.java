package com.socialuni.social.im.event;

import cn.hutool.http.HttpUtil;
import com.socialuni.social.common.sdk.event.ddd.AbstractJsonEventConsum;
import org.springframework.stereotype.Component;

import java.util.Map;

/**用于同步user信息到im系统里面去。
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 11:17
 * @since 1.0
 */
@Component
public class UserEventConsum extends AbstractJsonEventConsum {
    public static final String openImIp = "82.157.32.169";
    @Override
    public void consumEvent(Map<String, Object> stringObjectMap) {

    }

    @Override
    public String getMatchClassName() {
        return "com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO";
    }

    public static void main(String[] args) {
        String post = HttpUtil.post(openImIp + ":10002/auth/user_register", "{\n" +
                "  \"birth\": 0,\n" +
                "  \"email\": \"string\",\n" +
                "  \"ex\": \"string\",\n" +
                "  \"faceURL\": \"string\",\n" +
                "  \"gender\": 0,\n" +
                "  \"nickname\": \"string\",\n" +
                "  \"operationID\": \"string\",\n" +
                "  \"phoneNumber\": \"string\",\n" +
                "  \"platform\": 7,\n" +
                "  \"secret\": \"open_im_server\",\n" +
                "  \"userID\": \"string\"\n" +
                "}");
        System.out.println(post);
    }
}
