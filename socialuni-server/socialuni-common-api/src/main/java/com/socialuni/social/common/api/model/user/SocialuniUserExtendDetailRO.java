package com.socialuni.social.common.api.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SocialuniUserExtendDetailRO extends SocialuniUserDetailRO {
    //省
    private String provinceName;
    //市
    private String cityName;
    //区县
    private String districtName;

    /**
     * 距离,单位千米
     */
    private Double distance;

    public SocialuniUserExtendDetailRO(SocialuniUserExtendDetailRO userRO) {
        super(userRO);
        this.provinceName = userRO.getProvinceName();
        this.cityName = userRO.getCityName();
        this.districtName = userRO.getDistrictName();
        this.distance = userRO.getDistance();
    }

    public SocialuniUserExtendDetailRO(SocialuniUserDetailRO socialuniUserDetailRO) {
        super(socialuniUserDetailRO);
    }
}
