package com.socialuni.social.user.sdk.model.factory;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SocialuniUserShowROFactory {

    public static SocialuniUserShowRO getMineUserRO() {
        SocialuniUserDo userDO = SocialuniUserUtil.getMineUserNotNull();

        return SocialuniUserShowROFactory.getMineUserRO(userDO);
    }

    public static SocialuniUserShowRO getMineUserRO(SocialuniUserDo userDO) {
        //user基础信息

        return SocialuniUserShowROFactory.getUserRO(userDO, userDO.getUserId());
    }

    public static SocialuniUserShowRO getUserRO(SocialuniUserDo userDO) {
        //user基础信息
        return SocialuniUserShowROFactory.getUserRO(userDO, null);
    }

    public static SocialuniUserShowRO getUserROLook(SocialuniUserDo userDO, SocialuniUserDo lookUser) {
        return getUserRO(userDO, lookUser.getUserId());
    }

    public static SocialuniUserShowRO getUserRO(SocialuniUserDo userDO, Long lookUserId) {

        SocialuniUserRO socialuniUserRO = SocialuniUserROFactory.getUserRO(userDO);

        //user基础信息
        SocialuniUserShowRO user = new SocialuniUserShowRO(socialuniUserRO);
        if (ObjectUtils.isEmpty(lookUserId)) {
            user.setIsMine(false);
        } else {
            user.setIsMine(lookUserId.equals(userDO.getUserId()));
        }
        user.setCity(userDO.getCity());
        user.setType(userDO.getType());
        user.setBirthday(userDO.getBirthday());
        return user;
    }

    public static List<SocialuniUserShowRO> toList(List<SocialuniUserDo> socialuniUserDos, SocialuniUserDo mineUser) {
        return socialuniUserDos.stream().map(item -> SocialuniUserShowROFactory.getUserRO(item, mineUser.getUserId())).collect(Collectors.toList());
    }
}
