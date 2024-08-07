package com.socialuni.social.user.sdk.redis;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.user.sdk.model.RO.SocialDistrictRO;
import com.socialuni.social.user.sdk.factory.SocialDistrictROFactory;
import com.socialuni.social.user.sdk.repository.DistrictRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DistrictRedis {
    @Resource
    private DistrictRepository districtRepository;

    @Cacheable(cacheNames = "districtByAdCode", key = "#adCode")
    public Optional<SocialuniDistrictDO> findFirstOneByAdCode(String adCode) {
        return districtRepository.findFirstOneByAdCode(adCode);
    }

    /**
     * 查询热门下包含子节点，和所有省份不包含子节点
     *
     * @return
     */
    @Cacheable(cacheNames = "districtsHot")
    public List<SocialDistrictRO> getHotDistricts() {
        List<SocialDistrictRO> list = new ArrayList<>();
        list.add(getHotDistrict());
        //获取省，不包含子节点
        list.addAll(SocialDistrictROFactory.districtDOToVOS(getChinaProvinces()));
        return list;
    }

    /**
     * 显示全部的时候，热门+全部省份包含子节点
     *
     * @return
     */
    @Cacheable(cacheNames = "districtsAll")
    public List<SocialDistrictRO> getAllDistricts() {
        List<SocialDistrictRO> list = new ArrayList<>();
        list.add(getHotDistrict());

        //获取热门和热门的子节点
        //查出来所有，平级结构，按城市编码排序，省在市前，市在区县前
        List<SocialuniDistrictDO> districtDOS = getChinaProvinces();
        //递归设置子节点
        list.addAll(recurseSetChild(districtDOS));

        return list;
    }

    private List<SocialDistrictRO> recurseSetChildOnce(List<SocialuniDistrictDO> districts) {
        List<SocialDistrictRO> socialDistrictROS = new ArrayList<>();
        //遍历转vo设置子节点
        for (SocialuniDistrictDO district : districts) {
            SocialDistrictRO socialDistrictRO = SocialDistrictROFactory.getDistrictRO(district);
            //如果街道为空设置子节点
            if (StringUtils.isEmpty(district.getDistrictName())) {
                List<SocialuniDistrictDO> districtDOS = getByParentAdCode(district.getAdCode());
                socialDistrictRO.setChilds(SocialDistrictROFactory.districtDOToVOS(districtDOS));
            }
            socialDistrictROS.add(socialDistrictRO);
        }
        return socialDistrictROS;
    }


    //递归设置child
    private List<SocialDistrictRO> recurseSetChild(List<SocialuniDistrictDO> districts) {
        List<SocialDistrictRO> socialDistrictROS = new ArrayList<>();
        //遍历转vo设置子节点
        for (SocialuniDistrictDO district : districts) {
            SocialDistrictRO socialDistrictRO = SocialDistrictROFactory.getDistrictRO(district);
            //如果街道为空设置子节点
            if (StringUtils.isEmpty(district.getDistrictName())) {
                List<SocialuniDistrictDO> districtDOS = getByParentAdCode(district.getAdCode());
                socialDistrictRO.setChilds(this.recurseSetChild(districtDOS));
            }
            socialDistrictROS.add(socialDistrictRO);
        }
        return socialDistrictROS;
    }

    /**
     * 获取热门下的子节点获，取前20热门市区
     *
     * @return
     */
    private SocialDistrictRO getHotDistrict() {
        //获取热门和热门的子节点
        SocialDistrictRO hotDistrict = new SocialDistrictRO();
        hotDistrict.setAdName("热门");
        hotDistrict.setProvinceName("中国");
        hotDistrict.setAdCode("999999");
        List<SocialuniDistrictDO> districtDOS = districtRepository.findTop20ByDistrictCodeAndStatusOrderByCountDesc("1", SocialuniCommonStatus.enable);

        hotDistrict.setChilds(recurseSetChildOnce(districtDOS));
        return hotDistrict;
    }


    /**
     * 根据父节点获取子节点，内部不包含子节点
     *
     * @param parentAdCode
     * @return
     */
    private List<SocialuniDistrictDO> getByParentAdCode(String parentAdCode) {
        return districtRepository.findByParentAdCodeAndStatusOrderByAdCode(parentAdCode, SocialuniCommonStatus.enable);
    }

    private List<SocialuniDistrictDO> getChinaProvinces() {
        return districtRepository.findByParentAdCodeAndStatusOrderByAdCode(SocialuniConst.chinaDistrictCode, SocialuniCommonStatus.enable);
    }
}