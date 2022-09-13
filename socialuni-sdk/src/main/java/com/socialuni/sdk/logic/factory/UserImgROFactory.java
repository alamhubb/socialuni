package com.socialuni.sdk.logic.factory;


import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.model.RO.user.SocialuniUserImgRO;
import com.socialuni.sdk.utils.UnionIdDbUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserImgROFactory {
    public static SocialuniUserImgRO DOtoRO(SocialUserImgDO userImg) {
        SocialuniUserImgRO imgVO = new SocialuniUserImgRO();
        String uid = UnionIdDbUtil.getUidByUnionIdNotNull(userImg.getUnionId());
        imgVO.setId(uid);
        imgVO.setSrc(SocialAppConfig.getStaticResourceUrl() + userImg.getSrc());
        imgVO.setAspectRatio(userImg.getAspectRatio());
        return imgVO;
    }

    public static List<SocialuniUserImgRO> userImgDOToVOS(List<SocialUserImgDO> imgDOs) {
        return imgDOs.stream().map(UserImgROFactory::DOtoRO).collect(Collectors.toList());
    }
}
