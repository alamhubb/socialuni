package com.socialuni.social.peiwan.sdk.factory;

import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoImgDO;
import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanInfoImgRO;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;

import java.util.List;

public class SocialuniPeiwanInfoImgROFactory {
    public static SocialuniPeiwanInfoImgRO getPeiwanImgRO(SocialuniPeiwanInfoImgDO peiwanInfoDO) {
        SocialuniPeiwanInfoImgRO imgRO = new SocialuniPeiwanInfoImgRO();

        Long uid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(peiwanInfoDO.getUnionId());

        imgRO.setId(uid);
        imgRO.setOrder(peiwanInfoDO.getLevel());
        imgRO.setSrc(SocialuniSystemConst.getStaticResourceUrl() + peiwanInfoDO.getSrc());
        imgRO.setAspectRatio(peiwanInfoDO.getAspectRatio());

        return imgRO;
    }

    public static List<SocialuniPeiwanInfoImgRO> getPeiwanImgRoList(List<SocialuniPeiwanInfoImgDO> peiwanInfoDOS) {
        return ListConvertUtil.toList(SocialuniPeiwanInfoImgROFactory::getPeiwanImgRO, peiwanInfoDOS);
    }
}
