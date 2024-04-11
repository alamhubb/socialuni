package com.socialuni.social.user.sdk.dao.utils;

import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.common.sdk.utils.SocialuniRequestUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserExtendFriendLogDo;
import com.socialuni.social.user.sdk.utils.DistrictStoreUtils;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        //未开启则不记录
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

        SocialuniDistrictDO districtDO = DistrictStoreUtils.findFirstOneByAdCode(adCode);
        if (districtDO == null) {
            socialuniUserExtendFriendLogDo.setAdCode(SocialuniConst.chinaDistrictCode);
        } else {
            socialuniUserExtendFriendLogDo.setAdCode(adCode);
        }


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

}