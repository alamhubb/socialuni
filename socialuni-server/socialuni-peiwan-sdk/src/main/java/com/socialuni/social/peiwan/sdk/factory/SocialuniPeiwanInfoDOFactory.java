package com.socialuni.social.peiwan.sdk.factory;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoDO;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;

public class SocialuniPeiwanInfoDOFactory {
    public static SocialuniPeiwanInfoDO createPeiwanDO(SocialuniUserDo socialuniUserDo, SocialuniPeiwanInfoRO peiwanInfoAddQO) {
        SocialuniPeiwanInfoDO socialuniPeiwanInfoDO = new SocialuniPeiwanInfoDO();

        socialuniPeiwanInfoDO.setUserId(socialuniUserDo.getUserId());

        SocialuniPeiwanInfoDOFactory.createPeiwanDO(socialuniPeiwanInfoDO, peiwanInfoAddQO);

        socialuniPeiwanInfoDO.setCity("北京");
        return socialuniPeiwanInfoDO;
    }

    public static SocialuniPeiwanInfoDO createPeiwanDO(SocialuniPeiwanInfoDO socialuniPeiwanInfoDO, SocialuniPeiwanInfoRO peiwanInfoRO) {

        socialuniPeiwanInfoDO.setUsername(peiwanInfoRO.getUsername());
        socialuniPeiwanInfoDO.setLng(peiwanInfoRO.getLng());
        socialuniPeiwanInfoDO.setLat(peiwanInfoRO.getLat());
        socialuniPeiwanInfoDO.setCity(peiwanInfoRO.getCity());
        socialuniPeiwanInfoDO.setDistrict(peiwanInfoRO.getDistrict());
        if (!peiwanInfoRO.getAvatar().contains(SocialuniSystemConst.getStaticResourceUrl())) {
            socialuniPeiwanInfoDO.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + peiwanInfoRO.getAvatar());
        }

        return socialuniPeiwanInfoDO;
    }
}
