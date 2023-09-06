package com.socialuni.social.peiwan.sdk.factory;

import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoDO;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoImgDO;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanInfoImgRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;

import java.util.List;

public class SocialuniPeiwanInfoROFactory {
    public static SocialuniPeiwanInfoRO getPeiwanRO(SocialuniPeiwanInfoDO peiwanInfoDO) {
        SocialuniPeiwanInfoRO peiwanInfoRO = new SocialuniPeiwanInfoRO();

        String userId = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(peiwanInfoDO.getUserId());

        peiwanInfoRO.setUserId(userId);
        peiwanInfoRO.setLng(peiwanInfoDO.getLng());
        peiwanInfoRO.setLat(peiwanInfoDO.getLat());
        peiwanInfoRO.setCity(peiwanInfoDO.getCity());
        peiwanInfoRO.setDistrict(peiwanInfoDO.getDistrict());
        peiwanInfoRO.setAvatar(peiwanInfoDO.getAvatar());

        List<SocialuniPeiwanInfoImgDO> list = SocialuniUserRepositoryFacede.findByAllByUserIdAndStatusOrderByIdDesc(peiwanInfoDO.getUserId(), SocialuniPeiwanInfoImgDO.class);

        List<SocialuniPeiwanInfoImgRO> imgROES = SocialuniPeiwanInfoImgROFactory.getPeiwanImgRoList(list);

        peiwanInfoRO.setImgs(imgROES);

        return peiwanInfoRO;
    }

    public static List<SocialuniPeiwanInfoRO> getPeiwanRoList(List<SocialuniPeiwanInfoDO> peiwanInfoDOS) {
        return ListConvertUtil.toList(SocialuniPeiwanInfoROFactory::getPeiwanRO, peiwanInfoDOS);
//        return peiwanInfoDOS.stream().map(SocialuniPeiwanInfoROFactory::getPeiwanRO).collect(Collectors.toList());
    }
}
