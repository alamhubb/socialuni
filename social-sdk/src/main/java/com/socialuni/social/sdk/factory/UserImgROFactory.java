package com.socialuni.social.sdk.factory;


import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.RO.user.UserImgRO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserImgROFactory {
    public static UserImgRO DOtoRO(UserImgDO userImg) {
        UserImgRO imgVO = new UserImgRO();
        imgVO.setId(userImg.getId());
        //为空则异常
        if (StringUtils.isEmpty(SocialAppConfig.staticResourceUrl)) {
            throw new SocialParamsException("图片路径错误异常");
        }
        imgVO.setSrc(SocialAppConfig.staticResourceUrl + "/" + userImg.getSrc());
        imgVO.setAspectRatio(userImg.getAspectRatio());
        return imgVO;
    }

    public static List<UserImgRO> userImgDOToVOS(List<UserImgDO> imgDOs) {
        return imgDOs.stream().map(UserImgROFactory::DOtoRO).collect(Collectors.toList());
    }
}
