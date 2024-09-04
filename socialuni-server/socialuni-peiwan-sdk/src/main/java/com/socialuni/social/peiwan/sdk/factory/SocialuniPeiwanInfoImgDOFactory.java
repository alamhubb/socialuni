package com.socialuni.social.peiwan.sdk.factory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoImgDO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;

import java.util.ArrayList;
import java.util.List;

public class SocialuniPeiwanInfoImgDOFactory {
    public static SocialuniPeiwanInfoImgDO createPeiwanImgDO(Long userId, SocialuniImgAddQO imgAddQO, Integer order) {
        Long unionId = SocialuniUnionIdFacede.createPeiwanImgUnionId();

        SocialuniPeiwanInfoImgDO socialuniPeiwanInfoImgDO = new SocialuniPeiwanInfoImgDO(userId, unionId, SocialuniContentType.peiwanImg);

        socialuniPeiwanInfoImgDO.setSrc(imgAddQO.getSrc());
        socialuniPeiwanInfoImgDO.setLevel(order);
        socialuniPeiwanInfoImgDO.setAspectRatio(imgAddQO.getAspectRatio());
        socialuniPeiwanInfoImgDO.setQuality(imgAddQO.getQuality());
        socialuniPeiwanInfoImgDO.setSize(imgAddQO.getSize());

        return socialuniPeiwanInfoImgDO;
    }

    public static List<SocialuniPeiwanInfoImgDO> createPeiwanImgDOs(Long userId, List<SocialuniImgAddQO> imgAddQOS) {
        List<SocialuniPeiwanInfoImgDO> socialuniPeiwanInfoImgDOS = new ArrayList<>();

        for (SocialuniImgAddQO imgAddQO : imgAddQOS) {
            Integer index = imgAddQOS.indexOf(imgAddQO);
            Integer order = 9999;
            if (index > 0) {
                order = index;
            }
            SocialuniPeiwanInfoImgDO socialuniPeiwanInfoImgDO = SocialuniPeiwanInfoImgDOFactory.createPeiwanImgDO(userId, imgAddQO, order);
            socialuniPeiwanInfoImgDOS.add(socialuniPeiwanInfoImgDO);
        }
        return socialuniPeiwanInfoImgDOS;
    }
}
