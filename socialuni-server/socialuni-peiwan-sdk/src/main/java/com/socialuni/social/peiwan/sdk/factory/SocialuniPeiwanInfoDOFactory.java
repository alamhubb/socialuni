package com.socialuni.social.peiwan.sdk.factory;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoDO;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;

public class SocialuniPeiwanInfoDOFactory {
    public static SocialuniPeiwanInfoDO createPeiwanDO(SocialuniUserDo socialuniUserDo, SocialuniPeiwanInfoRO peiwanInfoAddQO) {
        SocialuniPeiwanInfoDO socialuniPeiwanInfoDO = new SocialuniPeiwanInfoDO();
        socialuniPeiwanInfoDO.setUserId(socialuniUserDo.getUserId());
        socialuniPeiwanInfoDO.setLng(peiwanInfoAddQO.getLng());
        socialuniPeiwanInfoDO.setLat(peiwanInfoAddQO.getLat());
        socialuniPeiwanInfoDO.setCity("北京");
        socialuniPeiwanInfoDO.setDistrict(peiwanInfoAddQO.getDistrict());
        socialuniPeiwanInfoDO.setAvatar(peiwanInfoAddQO.getAvatar());

        return socialuniPeiwanInfoDO;
    }
}
