package com.socialuni.social.user.sdk.dao.utils;

import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.common.sdk.utils.SocialuniRequestUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserExtendFriendLogDo;
import com.socialuni.social.user.sdk.utils.DistrictStoreUtils;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocialuniUserExtendFriendLogDOUtil {
    public static SocialuniUserExtendFriendLogDo createUserExtendFriendLog() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        if (mineUser == null) {
            return null;
        }
        return createUserExtendFriendLog(mineUser);
    }

    public static SocialuniUserExtendFriendLogDo createUserExtendFriendLog(SocialuniUserDo mineUser) {
        SocialuniUserExtendFriendLogDo socialuniUserExtendFriendLogDo = new SocialuniUserExtendFriendLogDo();
        socialuniUserExtendFriendLogDo.setUserId(mineUser.getUserId());

        String lon = SocialuniRequestUtil.getCityLon();
        String lat = SocialuniRequestUtil.getCityLat();
        String adCode = SocialuniRequestUtil.getCityAdCode();

        socialuniUserExtendFriendLogDo.setLat(lat);
        socialuniUserExtendFriendLogDo.setLon(lon);

        SocialuniDistrictDO districtDO = DistrictStoreUtils.findFirstOneByAdCode(adCode);
        if (districtDO == null) {
            socialuniUserExtendFriendLogDo.setAdCode(SocialuniConst.chinaDistrictCode);
        } else {
            socialuniUserExtendFriendLogDo.setAdCode(adCode);
        }


        socialuniUserExtendFriendLogDo = SocialuniUserRepositoryFacede.save(socialuniUserExtendFriendLogDo);

        return socialuniUserExtendFriendLogDo;
        /*CompletableFuture.supplyAsync(() -> {
            SocialuniUserRepositoryFacede.save(socialuniUserExtendFriendLogDo);
            return null;
        }).exceptionally(e -> {
            e.printStackTrace();
            log.info(e.getMessage());
            return null;
        });*/
    }


}
