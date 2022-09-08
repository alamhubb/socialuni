package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.CenterUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

@Component
public class CenterMineUserDetailROFactory {
    public static SocialuniMineUserDetailRO getMineUserDetail() {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        return CenterMineUserDetailROFactory.getMineUserDetail(mineUser);
    }

    public static SocialuniMineUserDetailRO getMineUserDetail(SocialuniUserDO mineUser) {
        //通过sdk获取当前用户详情
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        //通过父类直接生成子类
        return CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);
    }

    public static SocialuniMineUserDetailRO getMineUserDetail(SocialMineUserDetailRO socialMineUserDetailRO, SocialuniUserDO mineUser) {
        //为了复用代码，直接生成用户详情
        CenterUserDetailRO centerUserDetailRO = CenterUserDetailROFactory.getUserDetailRO(socialMineUserDetailRO, mineUser);
        //通过父类直接生成子类
        SocialuniMineUserDetailRO centerMineUserDetailRO = new SocialuniMineUserDetailRO(centerUserDetailRO, socialMineUserDetailRO);

        return centerMineUserDetailRO;
    }
}
