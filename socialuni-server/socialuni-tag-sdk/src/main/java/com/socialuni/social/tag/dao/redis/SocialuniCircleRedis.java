package com.socialuni.social.tag.dao.redis;

import com.socialuni.social.tag.dao.DO.SocialuniCircleDO;
import com.socialuni.social.tag.dao.DO.SocialuniTagTypeDO;
import com.socialuni.social.tag.dao.repository.SocialuniCircleRepository;
import com.socialuni.social.tag.dao.repository.SocialuniTagTypeRepository;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.tag.factory.SociaCircleTypeROFactory;
import com.socialuni.social.tag.factory.SocialCircleROFactory;
import com.socialuni.social.common.sdk.model.RO.SocialCircleRO;
import com.socialuni.social.tag.model.CircleTypeRO;
import com.socialuni.social.common.api.constant.GenderType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialuniCircleRedis {
    @Resource
    private SocialuniTagTypeRepository tagTypeRepository;
    @Resource
    private SocialuniCircleRepository socialCircleApi;

    /**
     * 查询热门下包含子节点，和所有省份不包含子节点
     *
     * @return
     */
    @Cacheable(cacheNames = "circlesHot", key = "#appGenderType")
    public List<SocialCircleRO> getHotCirclesRedis(String appGenderType) {
        List<?  extends SocialuniCircleDO> circles = socialCircleApi.findByStatusOrderByCountDesc(ContentStatus.enable, PageRequest.of(0, 10));

        List<SocialCircleRO> circleROS = SocialCircleROFactory.circleDOToROS(circles);
        return circleROS;
    }

    @Cacheable(cacheNames = "circleTypesHot", key = "#appGenderType")
    public List<CircleTypeRO> getHotCircleTypesRedis(String appGenderType) {
        List<CircleTypeRO> initCircleTypes = new ArrayList<>();
        //插入一个热门类别
        initCircleTypes.add(getHotCircleType(appGenderType));

        //转成vo
        List<?  extends SocialuniTagTypeDO> tagTypes = getCircleTypes();
        initCircleTypes.addAll(SociaCircleTypeROFactory.tagDOToROS(tagTypes));
        return initCircleTypes;
    }

    @Cacheable(cacheNames = "circleTypesAll", key = "#appGenderType")
    public List<CircleTypeRO> getAllCircleTypesRedis(String appGenderType) {
        List<CircleTypeRO> initCircleTypes = new ArrayList<>();
        //插入一个热门类别，根据性别获取一个
        initCircleTypes.add(getHotCircleType(appGenderType));
        //查询全部，但是不查询子节点
        List<?  extends SocialuniTagTypeDO> tagTypes = getCircleTypes();
        initCircleTypes.addAll(circleTypesSetCircles(tagTypes, appGenderType));
        return initCircleTypes;
    }

    //获取热门类型的tagtype
    private CircleTypeRO getHotCircleType(String appGenderType) {
        //插入一个热门类别
        CircleTypeRO hotCircleTypeRO = new CircleTypeRO();
        hotCircleTypeRO.setId(9999);
        hotCircleTypeRO.setName("热门");

        hotCircleTypeRO.setCircles(getHotCirclesRedis(appGenderType));
        return hotCircleTypeRO;
    }


    private List<?  extends SocialuniTagTypeDO> getCircleTypes() {
        //查询出来所有启用的类型的tagTypes，按talk数量排序
        return tagTypeRepository.findByStatusOrderByOrderLevelDescTalkCountDesc(ContentStatus.enable);
    }

    //给tagtype设置它的子标签
    private List<CircleTypeRO> circleTypesSetCircles(List<?  extends SocialuniTagTypeDO> DOs, String appGenderType) {
        List<CircleTypeRO> tagTypeROS = new ArrayList<>();
        for (SocialuniTagTypeDO tagTypeDO : DOs) {
            //如果为女生，且app为男生，则不查询
            if (tagTypeDO.getName().equals(GenderType.girlTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.boy)) {
                    //获取所有女生话题
                    CircleTypeRO tagTypeRO = SociaCircleTypeROFactory.getCircleTypeRO(tagTypeDO);
                    List<?  extends SocialuniCircleDO> tagDOS = socialCircleApi.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.girl);
                    tagTypeRO.setCircles(SocialCircleROFactory.circleDOToROS(tagDOS));
                    tagTypeROS.add(tagTypeRO);
                }
                //如果为男生专属，查询男生
            } else if (tagTypeDO.getName().equals(GenderType.boyTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.girl)) {
                    //获取所有男生话题
                    CircleTypeRO tagTypeRO = SociaCircleTypeROFactory.getCircleTypeRO(tagTypeDO);
                    List<?  extends SocialuniCircleDO> tagDOS = socialCircleApi.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.boy);
                    tagTypeRO.setCircles(SocialCircleROFactory.circleDOToROS(tagDOS));
                    tagTypeROS.add(tagTypeRO);
                }
            } else {
                CircleTypeRO tagTypeRO = SociaCircleTypeROFactory.getCircleTypeRO(tagTypeDO);
                tagTypeRO.setCircles(getCirclesByCircleTypeDOAndGender(tagTypeDO, appGenderType));
                tagTypeROS.add(tagTypeRO);
            }
        }
        return tagTypeROS;
    }

    //根据typeid获取所有
    public List<?  extends SocialuniCircleDO> getCirclesByCircleTypeId(Integer tagTypeId) {
        // 从数据库中获取tag列表
        return socialCircleApi.findByTagTypeIdAndStatusOrderByCountDesc(tagTypeId, ContentStatus.enable);
    }

    //根据typeid获取所有
    public List<SocialCircleRO> getCirclesByCircleTypeDOAndGender(SocialuniTagTypeDO tagTypeDO, String appGenderType) {
        List<?  extends SocialuniCircleDO> tagDOS;
        if (appGenderType.equals(GenderType.all)) {
            tagDOS = getCirclesByCircleTypeId(tagTypeDO.getId());
        } else {
            tagDOS = socialCircleApi.findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(tagTypeDO.getId(), ContentStatus.enable, appGenderType);
        }
        // 从数据库中获取tag列表
        return SocialCircleROFactory.circleDOToROS(tagDOS);
    }
}