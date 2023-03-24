package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserExtendDetailRO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.community.sdk.entity.DistrictDO;
import com.socialuni.social.community.sdk.utils.DistrictStoreUtils;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExtendFriendLogDo;
import com.socialuni.social.common.sdk.model.RO.SocialuniRectangleRO;
import com.socialuni.social.sdk.utils.PositionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniUserExtendDetailROFactory {
    public static SocialuniUserExtendDetailRO getUserExtendDetailRO(SocialuniUserDo userDO, SocialuniUserDo mineUser) {
        //user基础信息
        SocialuniUserDetailRO socialuniUserDetailRO = SocialuniUserDetailROFactory.getUserDetailRO(userDO, mineUser);

        //user基础信息
        SocialuniUserExtendDetailRO socialuniUserExtendDetailRO = new SocialuniUserExtendDetailRO(socialuniUserDetailRO);

        SocialuniUserExtendFriendLogDo socialuniUserExtendFriendLogDo = SocialuniUserRepositoryFacede.findFirstByUserId(userDO.getUserId(), SocialuniUserExtendFriendLogDo.class);

        if (socialuniUserExtendFriendLogDo == null) {
            throw new SocialParamsException("不该为空");
        }

        String adCode = socialuniUserExtendFriendLogDo.getAdCode();
        if (StringUtils.isEmpty(adCode)) {
            throw new SocialParamsException("不该为空");
        }

        DistrictDO districtDO = DistrictStoreUtils.findFirstOneByAdCode(adCode);


        socialuniUserExtendDetailRO.setCityName(districtDO.getCityName());
        socialuniUserExtendDetailRO.setProvinceName(districtDO.getProvinceName());
        socialuniUserExtendDetailRO.setDistrictName(districtDO.getDistrictName());


        String lon = socialuniUserExtendFriendLogDo.getLon();
        String lat = socialuniUserExtendFriendLogDo.getLat();

        SocialuniRectangleRO dataRO = new SocialuniRectangleRO(Double.valueOf(lon), Double.valueOf(lat));

        Double getDistance = PositionUtil.getDistance(dataRO);

        socialuniUserExtendDetailRO.setDistance(getDistance);

        return socialuniUserExtendDetailRO;
    }

    public static List<SocialuniUserExtendDetailRO> getUserExtendDetailList(List<SocialuniUserDo> userDOs, SocialuniUserDo mineUser) {
        return ListConvertUtil.toList(SocialuniUserExtendDetailROFactory::getUserExtendDetailRO, userDOs, mineUser);
    }
}
