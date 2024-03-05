package com.socialuni.social.user.sdk.factory;

import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.user.sdk.model.RO.SocialDistrictRO;
import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-30 14:08
 */
@Data
public class SocialDistrictROFactory {
    public static SocialDistrictRO getDistrictRO(SocialuniDistrictDO district) {
        SocialDistrictRO socialDistrictRO = new SocialDistrictRO();
        socialDistrictRO.setId(district.getId());
        socialDistrictRO.setProvinceName(district.getProvinceName());
        socialDistrictRO.setCityName(district.getCityName());
        socialDistrictRO.setDistrictName(district.getDistrictName());
        socialDistrictRO.setAdCode(district.getAdCode());
        socialDistrictRO.setAdName(district.getAdName());
        socialDistrictRO.setCount(district.getCount());
        socialDistrictRO.setTalkCount(district.getTalkCount());

        return socialDistrictRO;
    }


    public static List<SocialDistrictRO> districtDOToVOS(List<SocialuniDistrictDO> DOs) {
        return ListConvertUtil.toList(SocialDistrictROFactory::getDistrictRO, DOs);
    }
}
