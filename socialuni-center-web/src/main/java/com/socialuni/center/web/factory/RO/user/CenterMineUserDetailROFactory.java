package com.socialuni.center.web.factory.RO.user;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import org.springframework.stereotype.Component;

@Component
public class CenterMineUserDetailROFactory {
    public static CenterMineUserDetailRO getMineUserDetail() {
        UserDO mineUser = CenterUserUtil.getMineUserAllowNull();
        return CenterMineUserDetailROFactory.getMineUserDetail(mineUser);
    }

    public static CenterMineUserDetailRO getMineUserDetail(UserDO mineUser) {
        //通过sdk获取当前用户详情
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        //通过父类直接生成子类
        return CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);
    }

    public static CenterMineUserDetailRO getMineUserDetail(SocialMineUserDetailRO socialMineUserDetailRO, UserDO mineUser) {
        //为了复用代码，直接生成用户详情
        CenterUserDetailRO centerUserDetailRO = CenterUserDetailROFactory.getUserDetailRO(socialMineUserDetailRO, mineUser);
        //通过父类直接生成子类
        CenterMineUserDetailRO centerMineUserDetailRO = new CenterMineUserDetailRO(centerUserDetailRO, socialMineUserDetailRO);

        return centerMineUserDetailRO;
    }
}
