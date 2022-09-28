package com.socialuni.social.sdk.logic.factory;


import com.socialuni.social.sdk.dao.DO.user.SocialuniUserImgDO;
import com.socialuni.social.tance.config.SocialuniSystemConst;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserImgRO;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserImgROFactory {
    public static SocialuniUserImgRO DOtoRO(SocialuniUserImgDO userImg) {
        SocialuniUserImgRO imgVO = new SocialuniUserImgRO();
        String uid = SocialuniUnionIdUtil.getUuidByUnionIdNotNull(userImg.getUnionId());
        imgVO.setId(uid);
        imgVO.setSrc(SocialuniSystemConst.getStaticResourceUrl() + userImg.getSrc());
        imgVO.setAspectRatio(userImg.getAspectRatio());
        return imgVO;
    }

    public static List<SocialuniUserImgRO> userImgDOToVOS(List<SocialuniUserImgDO> imgDOs) {
        return imgDOs.stream().map(UserImgROFactory::DOtoRO).collect(Collectors.toList());
    }
}
