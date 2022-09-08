package com.socialuni.sdk.dao.store;

import com.socialuni.sdk.factory.community.SociaCircleTypeROFactory;
import com.socialuni.sdk.factory.community.SocialCircleROFactory;
import com.socialuni.sdk.utils.SocialTagStore;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.dao.DO.circle.SocialCircleDO;
import com.socialuni.sdk.dao.DO.tag.SocialuniTagTypeDO;
import com.socialuni.sdk.model.RO.talk.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.talk.circle.SocialCircleRO;
import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.repository.community.TagRepository;
import com.socialuni.sdk.dao.repository.community.SocialuniTagTypeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialuniCircleRedis {
    @Resource
    private TagRepository tagRepository;
    @Resource
    private SocialuniTagTypeRepository tagTypeRepository;
    @Resource
    private SocialTagStore socialTagStore;
    @Resource
    private SocialCircleRepository socialCircleRepository;

    /**
     * 查询热门下包含子节点，和所有省份不包含子节点
     *
     * @return
     */
    @Cacheable(cacheNames = "circlesHot", key = "#appGenderType")
    public List<SocialCircleRO> getHotCirclesRedis(String appGenderType) {
        List<SocialCircleDO> circles = socialCircleRepository.findByStatusOrderByCountDesc(ContentStatus.enable, PageRequest.of(0, 10));

        List<SocialCircleRO> circleROS = SocialCircleROFactory.circleDOToROS(circles);
        return circleROS;
    }

   /* @Cacheable(cacheNames = "circlesAll", key = "#appGenderType")
    public List<TagRO> getAllCirclesRedis(String appGenderType) {
        List<TagDO> tagDOS;
        if (appGenderType.equals(GenderType.all)) {
            tagDOS = tagRepository.findAllByStatusOrderByCountDesc(ContentStatus.enable);
        } else {
            tagDOS = tagRepository.findAllByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, appGenderType);
        }
        return SocialTagROFactory.tagDOToROS(tagDOS);
    }*/


  /*  @Cacheable(cacheNames = "circleTypesHot", key = "#appGenderType")
    public List<TagTypeRO> getHotCircleTypesRedis(String appGenderType) {
        List<TagTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别
        initTagTypes.add(getHotCircleType(appGenderType));

        //转成vo
        List<TagTypeDO> tagTypes = getCircleTypes();
        initTagTypes.addAll(SocialTagTypeROFactory.tagDOToROS(tagTypes));
        return initTagTypes;
    }
*/

    @Cacheable(cacheNames = "circleTypesAll", key = "#appGenderType")
    public List<CircleTypeRO> getAllCircleTypesRedis(String appGenderType) {
        List<CircleTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别，根据性别获取一个
        initTagTypes.add(getHotCircleType(appGenderType));
        //查询全部，但是不查询子节点
        List<SocialuniTagTypeDO> tagTypes = getCircleTypes();
        initTagTypes.addAll(circleTypesSetCircles(tagTypes, appGenderType));
        return initTagTypes;
    }


    //获取热门类型的tagtype
    private CircleTypeRO getHotCircleType(String appGenderType) {
        //插入一个热门类别
        CircleTypeRO hotTagTypeRO = new CircleTypeRO();
        hotTagTypeRO.setId(9999);
        hotTagTypeRO.setName("热门");

        hotTagTypeRO.setCircles(getHotCirclesRedis(appGenderType));
        return hotTagTypeRO;
    }


    private List<SocialuniTagTypeDO> getCircleTypes() {
        //查询出来所有启用的类型的tagTypes，按talk数量排序
        return tagTypeRepository.findByStatusAndTalkCountGreaterThanOrderByOrderLevelDescTalkCountDesc(ContentStatus.enable, SocialuniConst.zero);
    }

    //给tagtype设置它的子标签
    private List<CircleTypeRO> circleTypesSetCircles(List<SocialuniTagTypeDO> DOs, String appGenderType) {
        List<CircleTypeRO> tagTypeROS = new ArrayList<>();
        for (SocialuniTagTypeDO tagTypeDO : DOs) {
            //如果为女生，且app为男生，则不查询
            if (tagTypeDO.getName().equals(GenderType.girlTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.boy)) {
                    //获取所有女生话题
                    CircleTypeRO tagTypeRO = SociaCircleTypeROFactory.getCircleTypeRO(tagTypeDO);
                    List<SocialCircleDO> tagDOS = socialCircleRepository.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.girl);
                    tagTypeRO.setCircles(SocialCircleROFactory.circleDOToROS(tagDOS));
                    tagTypeROS.add(tagTypeRO);
                }
                //如果为男生专属，查询男生
            } else if (tagTypeDO.getName().equals(GenderType.boyTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.girl)) {
                    //获取所有男生话题
                    CircleTypeRO tagTypeRO = SociaCircleTypeROFactory.getCircleTypeRO(tagTypeDO);
                    List<SocialCircleDO> tagDOS = socialCircleRepository.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.boy);
                    tagTypeRO.setCircles(SocialCircleROFactory.circleDOToROS(tagDOS));
                    tagTypeROS.add(tagTypeRO);
                }
            } else {
                CircleTypeRO tagTypeRO = SociaCircleTypeROFactory.getCircleTypeRO(tagTypeDO);
                tagTypeRO.setCircles(getCirclesByTagTypeDOAndGender(tagTypeDO, appGenderType));
                tagTypeROS.add(tagTypeRO);
            }
        }
        return tagTypeROS;
    }

    //根据typeid获取所有
    public List<SocialCircleRO> getCirclesByTagTypeDOAndGender(SocialuniTagTypeDO tagTypeDO, String appGenderType) {
        List<SocialCircleDO> tagDOS;
        if (appGenderType.equals(GenderType.all)) {
            tagDOS = getCirclesByTagTypeId(tagTypeDO.getId());
        } else {
            tagDOS = socialCircleRepository.findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(tagTypeDO.getId(), ContentStatus.enable, appGenderType);
        }
        // 从数据库中获取tag列表
        return SocialCircleROFactory.circleDOToROS(tagDOS);
    }


    //根据typeid获取所有
    public List<SocialCircleDO> getCirclesByTagTypeId(Integer tagTypeId) {
        // 从数据库中获取tag列表
        return socialCircleRepository.findByTagTypeIdAndStatusOrderByCountDesc(tagTypeId, ContentStatus.enable);
    }
}