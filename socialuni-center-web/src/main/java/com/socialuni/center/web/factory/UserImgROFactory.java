package com.socialuni.center.web.factory;


import com.socialuni.center.web.model.DO.user.UserImgDO;
import com.socialuni.center.web.model.RO.user.UserImgRO;
import com.socialuni.center.web.config.SocialAppConfig;

import java.util.List;
import java.util.stream.Collectors;

public class UserImgROFactory {
    public static UserImgRO DOtoRO(UserImgDO userImg) {
        UserImgRO imgVO = new UserImgRO();
        imgVO.setId(userImg.getId());
        imgVO.setSrc(SocialAppConfig.getStaticResourceUrl() + userImg.getSrc());
        imgVO.setAspectRatio(userImg.getAspectRatio());
        return imgVO;
    }

    public static List<UserImgRO> userImgDOToVOS(List<UserImgDO> imgDOs) {
        return imgDOs.stream().map(UserImgROFactory::DOtoRO).collect(Collectors.toList());
    }
}
