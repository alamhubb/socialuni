package com.socialuni.social.peiwan.sdk.factory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoDO;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoImgDO;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import feign.form.ContentType;

import java.util.ArrayList;
import java.util.List;

public class SocialuniPeiwanInfoImgDOFactory {
    public static SocialuniPeiwanInfoImgDO createPeiwanImgDO(Integer userId, SocialuniImgAddQO imgAddQO) {
        Integer unionId = SocialuniUnionIdFacede.createPeiwanImgUnionId();

        SocialuniPeiwanInfoImgDO socialuniPeiwanInfoImgDO = new SocialuniPeiwanInfoImgDO(userId,unionId, SocialuniContentType.peiwanImg);

        socialuniPeiwanInfoImgDO.setSrc(imgAddQO.getSrc());
        socialuniPeiwanInfoImgDO.setAspectRatio(imgAddQO.getAspectRatio());
        socialuniPeiwanInfoImgDO.setQuality(imgAddQO.getQuality());
        socialuniPeiwanInfoImgDO.setSize(imgAddQO.getSize());

        return socialuniPeiwanInfoImgDO;
    }

    public static List<SocialuniPeiwanInfoImgDO> createPeiwanImgDOs(Integer userId, List<SocialuniImgAddQO> imgAddQOS) {
        List<SocialuniPeiwanInfoImgDO> socialuniPeiwanInfoImgDOS = new ArrayList<>();
        for (SocialuniImgAddQO imgAddQO : imgAddQOS) {
            SocialuniPeiwanInfoImgDO socialuniPeiwanInfoImgDO = SocialuniPeiwanInfoImgDOFactory.createPeiwanImgDO(userId, imgAddQO);
            socialuniPeiwanInfoImgDOS.add(socialuniPeiwanInfoImgDO);
        }
        return socialuniPeiwanInfoImgDOS;
    }
}
