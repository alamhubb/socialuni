package com.socialuni.social.user.sdk.model.factory;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import java.util.List;
import java.util.stream.Collectors;

public class SocialuniUserROFactory {

    public static SocialuniUserRO getMineUserRO() {
        SocialuniUserDo userDO = SocialuniUserUtil.getMineUserNotNull();

        return SocialuniUserROFactory.getMineUserRO(userDO);
    }

    public static SocialuniUserRO getMineUserRO(SocialuniUserDo userDO) {
        //user基础信息

        return SocialuniUserROFactory.getUserRO(userDO, userDO);
    }

    public static SocialuniUserRO getUserRO(SocialuniUserDo userDO, SocialuniUserDo lookUser) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserRO();
        String userUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userDO.getUnionId());
        if (lookUser == null) {
            user.setIsMine(false);
        } else {
            user.setIsMine(lookUser.getUserId().equals(userDO.getUserId()));
        }
        user.setId(userUid);
        user.setAge(userDO.getAge());
        user.setCity(userDO.getCity());
        user.setType(userDO.getType());
        user.setNickname(userDO.getNickname());
        user.setBirthday(userDO.getBirthday());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

    public static List<SocialuniUserRO> toList(List<SocialuniUserDo> socialuniUserDos, SocialuniUserDo mineUser) {
        return socialuniUserDos.stream().map(item -> SocialuniUserROFactory.getUserRO(item, mineUser)).collect(Collectors.toList());
    }
}
