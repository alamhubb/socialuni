package com.socialuni.sdk.factory;


import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.model.RO.user.UserImgRO;
import com.socialuni.sdk.config.SocialAppConfig;

import java.util.List;
import java.util.stream.Collectors;

public class UserImgROFactory {
    public static UserImgRO DOtoRO(SocialUserImgDO userImg) {
        UserImgRO imgVO = new UserImgRO();
        imgVO.setId(userImg.getUnionId());
        imgVO.setSrc(SocialAppConfig.getStaticResourceUrl() + userImg.getSrc());
        imgVO.setAspectRatio(userImg.getAspectRatio());
        return imgVO;
    }

    public static List<UserImgRO> userImgDOToVOS(List<SocialUserImgDO> imgDOs) {
        return imgDOs.stream().map(UserImgROFactory::DOtoRO).collect(Collectors.toList());
    }
}
