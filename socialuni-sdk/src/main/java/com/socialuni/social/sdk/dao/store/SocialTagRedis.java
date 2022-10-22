package com.socialuni.social.sdk.dao.store;

import com.socialuni.social.community.sdk.model.TagModel;
import com.socialuni.social.sdk.logic.factory.community.SocialTagROFactory;
import com.socialuni.social.sdk.logic.factory.community.SocialTagTypeROFactory;
import com.socialuni.social.sdk.utils.SocialTagStore;
import com.socialuni.social.common.enumeration.CommonStatus;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.community.sdk.model.SocialuniTagTypeModel;
import com.socialuni.social.sdk.model.RO.community.tag.TagRO;
import com.socialuni.social.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.social.community.sdk.enumeration.TagRedisKey;
import com.socialuni.social.community.sdk.api.TagInterface;
import com.socialuni.social.community.sdk.api.SocialuniTagTypeInterface;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialTagRedis {
    @Resource
    private TagInterface tagApi;
    @Resource
    private SocialuniTagTypeInterface tagTypeRepository;
    @Resource
    private SocialTagStore socialTagStore;

    @Cacheable(cacheNames = TagRedisKey.tagById, key = "#tagId")
    public TagModel findTagById(Integer tagId) {
        return tagApi.findByIdAndStatus(tagId, CommonStatus.enable);
    }

    @Cacheable(cacheNames = TagRedisKey.talkTagsByTalkId, key = "#talkId")
    public List<?  extends TagModel> getTagsByTalkId(Integer talkId) {
        List<Integer> tagIds = this.getTagIdsByTalkId(talkId);
        return socialTagStore.findTagsByIds(tagIds);
    }

    //获取talk下的
    private List<Integer> getTagIdsByTalkId(Integer talkId) {
        return tagApi.findTagIdsByTalkIdAndStatusAndShowFront(talkId, ContentStatus.enable, true);
    }

    /**
     * 查询热门下包含子节点，和所有省份不包含子节点
     *
     * @return
     */
    @Cacheable(cacheNames = "tagsHot", key = "#appGenderType")
    public List<TagRO> getHotTagsRedis(String appGenderType) {
        List<?  extends TagModel> tagModels;
        if (GenderType.all.equals(appGenderType)) {
            tagModels = tagApi.findByStatusOrderByCountDesc(ContentStatus.enable, PageRequest.of(0, 10));
        } else {
            tagModels = tagApi.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, appGenderType, PageRequest.of(0, 10));
        }
        return SocialTagROFactory.tagDOToROS(tagModels);
    }

    @Cacheable(cacheNames = "tagsAll", key = "#appGenderType")
    public List<TagRO> getAllTagsRedis(String appGenderType) {
        List<?  extends TagModel> tagModels;
        if (appGenderType.equals(GenderType.all)) {
            tagModels = tagApi.findAllByStatusOrderByCountDesc(ContentStatus.enable);
        } else {
            tagModels = tagApi.findAllByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, appGenderType);
        }
        return SocialTagROFactory.tagDOToROS(tagModels);
    }


    @Cacheable(cacheNames = "tagTypesHot", key = "#appGenderType")
    public List<TagTypeRO> getHotTagTypesRedis(String appGenderType) {
        List<TagTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别
        initTagTypes.add(getHotTagType(appGenderType));

        //转成vo
        List<?  extends SocialuniTagTypeModel> tagTypes = getTagTypes();
        initTagTypes.addAll(SocialTagTypeROFactory.tagDOToROS(tagTypes));
        return initTagTypes;
    }


    @Cacheable(cacheNames = "tagTypesAll", key = "#appGenderType")
    public List<TagTypeRO> getAllTageTypesRedis(String appGenderType) {
        List<TagTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别，根据性别获取一个
        initTagTypes.add(getHotTagType(appGenderType));
        //查询全部，但是不查询子节点
        List<?  extends SocialuniTagTypeModel> tagTypes = getTagTypes();
        initTagTypes.addAll(tagTypesSetTags(tagTypes, appGenderType));
        return initTagTypes;
    }


    //获取热门类型的tagtype
    private TagTypeRO getHotTagType(String appGenderType) {
        //插入一个热门类别
        TagTypeRO hotTagTypeRO = new TagTypeRO();
        hotTagTypeRO.setId(9999);
        hotTagTypeRO.setName("热门");

        hotTagTypeRO.setTags(getHotTagsRedis(appGenderType));
        return hotTagTypeRO;
    }


    private List<?  extends SocialuniTagTypeModel> getTagTypes() {
        //查询出来所有启用的类型的tagTypes，按talk数量排序
        return tagTypeRepository.findByStatusOrderByOrderLevelDescTalkCountDesc(ContentStatus.enable);
    }

    //给tagtype设置它的子标签
    private List<TagTypeRO> tagTypesSetTags(List<?  extends SocialuniTagTypeModel> DOs, String appGenderType) {
        List<TagTypeRO> tagTypeROS = new ArrayList<>();
        for (SocialuniTagTypeModel tagTypeDO : DOs) {
            //如果为女生，且app为男生，则不查询
            if (tagTypeDO.getName().equals(GenderType.girlTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.boy)) {
                    //获取所有女生话题
                    TagTypeRO tagTypeRO = SocialTagTypeROFactory.getTagTypeRO(tagTypeDO);
                    List<?  extends TagModel> tagModels = tagApi.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.girl);
                    tagTypeRO.setTags(SocialTagROFactory.tagDOToROS(tagModels));
                    tagTypeROS.add(tagTypeRO);
                }
                //如果为男生专属，查询男生
            } else if (tagTypeDO.getName().equals(GenderType.boyTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.girl)) {
                    //获取所有男生话题
                    TagTypeRO tagTypeRO = SocialTagTypeROFactory.getTagTypeRO(tagTypeDO);
                    List<?  extends TagModel> tagModels = tagApi.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.boy);
                    tagTypeRO.setTags(SocialTagROFactory.tagDOToROS(tagModels));
                    tagTypeROS.add(tagTypeRO);
                }
            } else {
                TagTypeRO tagTypeRO = SocialTagTypeROFactory.getTagTypeRO(tagTypeDO);
                tagTypeRO.setTags(getTagsByTagTypeId(tagTypeDO, appGenderType));
                tagTypeROS.add(tagTypeRO);
            }
        }
        return tagTypeROS;
    }

    //根据typeid获取所有
    private List<TagRO> getTagsByTagTypeId(SocialuniTagTypeModel tagTypeDO, String appGenderType) {
        List<?  extends TagModel> tagModels;
        if (appGenderType.equals(GenderType.all)) {
            tagModels = tagApi.findByTagTypeIdAndStatusOrderByCountDesc(tagTypeDO.getId(), ContentStatus.enable);
        } else {
            tagModels = tagApi.findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(tagTypeDO.getId(), ContentStatus.enable, appGenderType);
        }
        // 从数据库中获取tag列表
        return SocialTagROFactory.tagDOToROS(tagModels);
    }
}