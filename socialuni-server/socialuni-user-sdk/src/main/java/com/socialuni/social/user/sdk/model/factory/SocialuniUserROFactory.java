package com.socialuni.social.user.sdk.model.factory;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;

import java.util.List;
import java.util.stream.Collectors;

public class SocialuniUserROFactory {
    public static SocialuniUserRO getUserRO(SocialuniUserDo userDO, SocialuniUserDo mineUser) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserRO();
        String userUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userDO.getUnionId());
        if (mineUser == null) {
            user.setIsMine(false);
        } else {
            user.setIsMine(mineUser.getUserId().equals(userDO.getUserId()));
        }
        user.setId(userUid);
        user.setAge(userDO.getAge());
        user.setCity(userDO.getCity());
        user.setType(userDO.getType());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

    public static List<SocialuniUserRO> toList(List<SocialuniUserDo> socialuniUserDos, SocialuniUserDo mineUser) {
        return socialuniUserDos.stream().map(item -> SocialuniUserROFactory.getUserRO(item, mineUser)).collect(Collectors.toList());
    }
}