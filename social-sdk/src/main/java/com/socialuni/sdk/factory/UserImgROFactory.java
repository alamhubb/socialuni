package com.socialuni.sdk.factory;


import com.socialuni.sdk.model.DO.user.UserImgDO;
import com.socialuni.social.model.model.RO.user.UserImgRO;

import java.util.List;
import java.util.stream.Collectors;

public class UserImgROFactory {
    public static UserImgRO DOtoRO(UserImgDO userImg) {
        UserImgRO imgVO = new UserImgRO();
        imgVO.setId(userImg.getId().toString());
        imgVO.setSrc(userImg.getSrc());
        imgVO.setAspectRatio(userImg.getAspectRatio());
        return imgVO;
    }

    public static List<UserImgRO> userImgDOToVOS(List<UserImgDO> imgDOs) {
        return imgDOs.stream().map(UserImgROFactory::DOtoRO).collect(Collectors.toList());
    }
}
