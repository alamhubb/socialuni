package com.socialuni.demo.utils;

import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.demo.model.MineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.sdk.utils.model.SocialUserAccountUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserUtil {
    private static SocialuniUserAPI socialuniUserAPI;

    @Resource
    public void setSocialuniUserAPI(SocialuniUserAPI socialuniUserAPI) {
        UserUtil.socialuniUserAPI = socialuniUserAPI;
    }

    public static MineUserDetailRO getMineUser() {
        UserDO mineUser = SocialUserUtil.getMineUserNotNull();
        return UserUtil.getMineUser(mineUser);
    }

    public static MineUserDetailRO getMineUser(UserDO mineUser) {
        Integer mineUserId = mineUser.getId();
        SocialUserAccountDO socialUserAccountDO = SocialUserAccountUtil.getUserSocialAccount(mineUserId);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        CenterMineUserDetailRO centerMineUserDetailRO = new CenterMineUserDetailRO(socialMineUserDetailRO);
        MineUserDetailRO mineUserDetailRO = new MineUserDetailRO(centerMineUserDetailRO);
        if (socialUserAccountDO != null) {
            //如果不为null，则从联盟获取用户信息
            ResultRO<CenterMineUserDetailRO> centerMineUserDetailROResultRO = socialuniUserAPI.getMineUser();
            CenterMineUserDetailRO socialuniMineUserDetailRO = centerMineUserDetailROResultRO.getData();
            //主要是设置联盟的Id
            mineUserDetailRO.setId(socialuniMineUserDetailRO.getId());
            //设置联盟的昵称
            mineUserDetailRO.setSocialuniNickname(socialuniMineUserDetailRO.getNickname());
            mineUserDetailRO.setBindedSocialuni(true);
        } else {
            mineUserDetailRO.setId(mineUserId.toString());
            mineUserDetailRO.setBindedSocialuni(false);
        }
        return mineUserDetailRO;
    }


    public static MineUserDetailRO getMineUser(SocialMineUserDetailRO socialMineUserDetailRO) {
        Integer mineUserId = socialMineUserDetailRO.getId();
        SocialUserAccountDO socialUserAccountDO = SocialUserAccountUtil.getUserSocialAccount(mineUserId);

        CenterMineUserDetailRO centerMineUserDetailRO = new CenterMineUserDetailRO(socialMineUserDetailRO);
        MineUserDetailRO mineUserDetailRO = new MineUserDetailRO(centerMineUserDetailRO);
        if (socialUserAccountDO != null) {
            //如果不为null，则从联盟获取用户信息
            ResultRO<CenterMineUserDetailRO> centerMineUserDetailROResultRO = socialuniUserAPI.getMineUser();
            CenterMineUserDetailRO socialuniMineUserDetailRO = centerMineUserDetailROResultRO.getData();
            //主要是设置联盟的Id
            mineUserDetailRO.setId(socialuniMineUserDetailRO.getId());
            //设置联盟的昵称
            mineUserDetailRO.setSocialuniNickname(socialuniMineUserDetailRO.getNickname());
            mineUserDetailRO.setBindedSocialuni(true);
        } else {
            mineUserDetailRO.setId(mineUserId.toString());
            mineUserDetailRO.setBindedSocialuni(false);
        }
        return mineUserDetailRO;
    }
}
