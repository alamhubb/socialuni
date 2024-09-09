package com.socialuni.social.user.sdk.model.factory;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import java.util.List;
import java.util.stream.Collectors;

public class SocialuniUserShowROFactory {

    public static SocialuniUserShowRO getMineUserRO() {
        SocialuniUserDo userDO = SocialuniUserUtil.getMineUserNotNull();

        return SocialuniUserShowROFactory.getMineUserRO(userDO);
    }

    public static SocialuniUserShowRO getMineUserRO(SocialuniUserDo userDO) {
        //user基础信息

        return SocialuniUserShowROFactory.getUserRO(userDO, userDO);
    }

    public static SocialuniUserShowRO getUserRO(SocialuniUserDo userDO, SocialuniUserDo lookUser) {

        SocialuniUserRO socialuniUserRO = SocialuniUserROFactory.getUserRO(userDO);
        //user基础信息
        SocialuniUserShowRO user = new SocialuniUserShowRO(socialuniUserRO);
        if (lookUser == null) {
            user.setIsMine(false);
        } else {
            user.setIsMine(lookUser.getUserId().equals(userDO.getUserId()));
        }
        user.setCity(userDO.getCity());
        user.setType(userDO.getType());
        user.setBirthday(userDO.getBirthday());
        return user;
    }

    public static List<SocialuniUserShowRO> toList(List<SocialuniUserDo> socialuniUserDos, SocialuniUserDo mineUser) {
        return socialuniUserDos.stream().map(item -> SocialuniUserShowROFactory.getUserRO(item, mineUser)).collect(Collectors.toList());
    }
}
