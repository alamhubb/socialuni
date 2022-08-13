package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.CenterUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

@Component
public class CenterMineUserDetailROFactory {
    public static CenterMineUserDetailRO getMineUserDetail() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();
        return CenterMineUserDetailROFactory.getMineUserDetail(mineUser);
    }

    public static CenterMineUserDetailRO getMineUserDetail(SocialUserDO mineUser) {
        //通过sdk获取当前用户详情
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        //通过父类直接生成子类
        return CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);
    }

    public static CenterMineUserDetailRO getMineUserDetail(SocialMineUserDetailRO socialMineUserDetailRO, SocialUserDO mineUser) {
        //为了复用代码，直接生成用户详情
        CenterUserDetailRO centerUserDetailRO = CenterUserDetailROFactory.getUserDetailRO(socialMineUserDetailRO, mineUser);
        //通过父类直接生成子类
        CenterMineUserDetailRO centerMineUserDetailRO = new CenterMineUserDetailRO(centerUserDetailRO, socialMineUserDetailRO);

        return centerMineUserDetailRO;
    }
}
