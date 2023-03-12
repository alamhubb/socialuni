package com.socialuni.social.sdk.dao.utils.user;

import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.sdk.facade.SocialuniUserRepositoryFacede;
import com.socialuni.social.sdk.utils.SocialuniRequestUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExtendFriendLogDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class SocialuniUserExtendFriendLogDOUtil {
    public static void createUserExtendFriendLog() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        if (mineUser == null) {
            return;
        }

        //判断用户是否打开了
        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getAllowNull(mineUser.getUserId());
        if (socialuniUserExpandDo == null) {
            return;
        }
        if (!socialuniUserExpandDo.getOpenContactInfo()) {
            return;
        }

        SocialuniUserExtendFriendLogDo socialuniUserExtendFriendLogDo = new SocialuniUserExtendFriendLogDo();
        socialuniUserExtendFriendLogDo.setUserId(mineUser.getUserId());

        String lon = SocialuniRequestUtil.getCityLon();
        String lat = SocialuniRequestUtil.getCityLat();
        String adCode = SocialuniRequestUtil.getCityAdCode();

        socialuniUserExtendFriendLogDo.setLat(lat);
        socialuniUserExtendFriendLogDo.setLon(lon);
        socialuniUserExtendFriendLogDo.setAdCode(adCode);

        SocialuniUserRepositoryFacede.save(socialuniUserExtendFriendLogDo);

        /*CompletableFuture.supplyAsync(() -> {
            SocialuniUserRepositoryFacede.save(socialuniUserExtendFriendLogDo);
            return null;
        }).exceptionally(e -> {
            e.printStackTrace();
            log.info(e.getMessage());
            return null;
        });*/
    }

    public static void findFirstUserExtendLogDOByIdDesc() {

    }

}
