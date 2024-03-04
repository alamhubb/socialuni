package com.socialuni.social.sdk.logic.factory;


import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserImgDo;

import java.util.List;
import java.util.stream.Collectors;

public class UserImgROFactory {
    public static SocialuniUserImgRO DOtoRO(SocialuniUserImgDo userImg) {
        SocialuniUserImgRO imgVO = new SocialuniUserImgRO();
        String uid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userImg.getUnionId());
        imgVO.setId(uid);
        imgVO.setSrc(SocialuniSystemConst.getStaticResourceUrl() + userImg.getSrc());
        imgVO.setAspectRatio(userImg.getAspectRatio());
        return imgVO;
    }

    public static List<SocialuniUserImgRO> userImgDOToVOS(List<SocialuniUserImgDo> imgDOs) {
        return imgDOs.stream().map(UserImgROFactory::DOtoRO).collect(Collectors.toList());
    }
}
