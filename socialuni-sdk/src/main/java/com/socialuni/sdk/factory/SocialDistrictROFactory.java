package com.socialuni.sdk.factory;

import com.socialuni.sdk.dao.DO.DistrictDO;
import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-30 14:08
 */
@Data
public class SocialDistrictROFactory {
    public static SocialDistrictRO getDistrictRO(DistrictDO district) {
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


    public static List<SocialDistrictRO> districtDOToVOS(List<DistrictDO> DOs) {
        return ListConvertUtil.toList(SocialDistrictROFactory::getDistrictRO, DOs);
    }
}
