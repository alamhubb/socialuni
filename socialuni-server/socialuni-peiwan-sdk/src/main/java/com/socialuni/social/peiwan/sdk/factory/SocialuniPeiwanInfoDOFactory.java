package com.socialuni.social.peiwan.sdk.factory;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoDO;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanInfoImgRO;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SocialuniPeiwanInfoDOFactory {
    public static SocialuniPeiwanInfoDO createPeiwanDO(SocialuniUserDo socialuniUserDo, SocialuniPeiwanInfoRO peiwanInfoAddQO) {
        SocialuniPeiwanInfoDO socialuniPeiwanInfoDO = new SocialuniPeiwanInfoDO();

        socialuniPeiwanInfoDO.setUserId(socialuniUserDo.getUserId());
        socialuniPeiwanInfoDO.setNickname(socialuniUserDo.getNickname());

        SocialuniPeiwanInfoDOFactory.createPeiwanDO(socialuniPeiwanInfoDO, peiwanInfoAddQO);

        List<SocialuniPeiwanInfoImgRO> imgs = peiwanInfoAddQO.getImgs();

        if (imgs.size() > 0) {
            List<SocialuniImgAddQO> list = new ArrayList<>();
            for (SocialuniPeiwanInfoImgRO img : imgs) {
                SocialuniImgAddQO addQO = new SocialuniImgAddQO();
                addQO.setAspectRatio(img.getAspectRatio());
                addQO.setSrc(img.getSrc());
            }
            SocialuniPeiwanInfoImgDOFactory.createPeiwanImgDOs(socialuniUserDo.getUserId(), list);
        }

        socialuniPeiwanInfoDO.setCity("北京");
        return socialuniPeiwanInfoDO;
    }

    public static SocialuniPeiwanInfoDO createPeiwanDO(SocialuniPeiwanInfoDO socialuniPeiwanInfoDO, SocialuniPeiwanInfoRO peiwanInfoRO) {

        socialuniPeiwanInfoDO.setNickname(peiwanInfoRO.getNickname());
        socialuniPeiwanInfoDO.setLng(peiwanInfoRO.getLng());
        socialuniPeiwanInfoDO.setLat(peiwanInfoRO.getLat());
        socialuniPeiwanInfoDO.setCity(peiwanInfoRO.getCity());
        socialuniPeiwanInfoDO.setDistrict(peiwanInfoRO.getDistrict());
        if (StringUtils.isNotBlank(peiwanInfoRO.getAvatar())) {
            if (!peiwanInfoRO.getAvatar().contains(SocialuniSystemConst.getStaticResourceUrl())) {
                socialuniPeiwanInfoDO.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + peiwanInfoRO.getAvatar());
            }
        }

        return socialuniPeiwanInfoDO;
    }
}
