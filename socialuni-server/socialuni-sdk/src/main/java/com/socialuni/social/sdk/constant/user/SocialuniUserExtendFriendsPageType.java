package com.socialuni.social.sdk.constant.user;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-03-07 18:32
 */
public class SocialuniUserExtendFriendsPageType {
    public static final String hot = "热门";
    public static final String recently = "最近在线";
    public static final String city = "同城";
    public static final List<String> allTypes = Arrays.asList(
            SocialuniUserExtendFriendsPageType.recently,
            SocialuniUserExtendFriendsPageType.hot,
            SocialuniUserExtendFriendsPageType.city
    );
}
