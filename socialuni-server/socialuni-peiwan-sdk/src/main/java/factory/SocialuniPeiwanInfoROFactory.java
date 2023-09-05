package factory;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import model.DO.SocialuniPeiwanInfoDO;
import model.QO.SocialuniPeiwanInfoRO;

import java.util.List;
import java.util.stream.Collectors;

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

        return peiwanInfoRO;
    }

    public static List<SocialuniPeiwanInfoRO> getPeiwanRoList(List<SocialuniPeiwanInfoDO> peiwanInfoDOS) {
        return ListConvertUtil.toList(SocialuniPeiwanInfoROFactory::getPeiwanRO, peiwanInfoDOS);
//        return peiwanInfoDOS.stream().map(SocialuniPeiwanInfoROFactory::getPeiwanRO).collect(Collectors.toList());
    }
}
