package com.socialuni.social.common.sdk.dao.store;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTagTypeDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTagDO;
import com.socialuni.social.community.sdk.enumeration.TagRedisKey;
import com.socialuni.social.community.sdk.repository.SocialuniTagTypeRepository;
import com.socialuni.social.community.sdk.repository.TagRepository;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.common.sdk.logic.factory.community.SocialTagROFactory;
import com.socialuni.social.common.sdk.logic.factory.community.SocialTagTypeROFactory;
import com.socialuni.social.common.sdk.model.RO.community.tag.TagRO;
import com.socialuni.social.common.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.social.common.sdk.utils.SocialTagStore;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialTagRedis {
    @Resource
    private TagRepository tagApi;
    @Resource
    private SocialuniTagTypeRepository tagTypeRepository;
    @Resource
    private SocialTagStore socialTagStore;

    @Cacheable(cacheNames = TagRedisKey.tagById, key = "#tagId")
    public SocialuniTagDO findTagById(Integer tagId) {
        return tagApi.findByIdAndStatus(tagId, SocialuniCommonStatus.enable);
    }

    @Cacheable(cacheNames = TagRedisKey.talkTagsByTalkId, key = "#talkId")
    public List<?  extends SocialuniTagDO> getTagsByTalkId(Integer talkId) {
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
        List<?  extends SocialuniTagDO> TagDOs;
        if (GenderType.all.equals(appGenderType)) {
            TagDOs = tagApi.findByStatusOrderByCountDesc(ContentStatus.enable, PageRequest.of(0, 10));
        } else {
            TagDOs = tagApi.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, appGenderType, PageRequest.of(0, 10));
        }
        return SocialTagROFactory.tagDOToROS(TagDOs);
    }

    @Cacheable(cacheNames = "tagsAll", key = "#appGenderType")
    public List<TagRO> getAllTagsRedis(String appGenderType) {
        List<?  extends SocialuniTagDO> TagDOs;
        if (appGenderType.equals(GenderType.all)) {
            TagDOs = tagApi.findAllByStatusOrderByCountDesc(ContentStatus.enable);
        } else {
            TagDOs = tagApi.findAllByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, appGenderType);
        }
        return SocialTagROFactory.tagDOToROS(TagDOs);
    }


    @Cacheable(cacheNames = "tagTypesHot", key = "#appGenderType")
    public List<TagTypeRO> getHotTagTypesRedis(String appGenderType) {
        List<TagTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别
        initTagTypes.add(getHotTagType(appGenderType));

        //转成vo
        List<?  extends SocialuniTagTypeDO> tagTypes = getTagTypes();
        initTagTypes.addAll(SocialTagTypeROFactory.tagDOToROS(tagTypes));
        return initTagTypes;
    }


    @Cacheable(cacheNames = "tagTypesAll", key = "#appGenderType")
    public List<TagTypeRO> getAllTageTypesRedis(String appGenderType) {
        List<TagTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别，根据性别获取一个
        initTagTypes.add(getHotTagType(appGenderType));
        //查询全部，但是不查询子节点
        List<?  extends SocialuniTagTypeDO> tagTypes = getTagTypes();
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


    private List<?  extends SocialuniTagTypeDO> getTagTypes() {
        //查询出来所有启用的类型的tagTypes，按talk数量排序
        return tagTypeRepository.findByStatusOrderByOrderLevelDescTalkCountDesc(ContentStatus.enable);
    }

    //给tagtype设置它的子标签
    private List<TagTypeRO> tagTypesSetTags(List<?  extends SocialuniTagTypeDO> DOs, String appGenderType) {
        List<TagTypeRO> tagTypeROS = new ArrayList<>();
        for (SocialuniTagTypeDO tagTypeDO : DOs) {
            //如果为女生，且app为男生，则不查询
            if (tagTypeDO.getName().equals(GenderType.girlTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.boy)) {
                    //获取所有女生话题
                    TagTypeRO tagTypeRO = SocialTagTypeROFactory.getTagTypeRO(tagTypeDO);
                    List<?  extends SocialuniTagDO> TagDOs = tagApi.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.girl);
                    tagTypeRO.setTags(SocialTagROFactory.tagDOToROS(TagDOs));
                    tagTypeROS.add(tagTypeRO);
                }
                //如果为男生专属，查询男生
            } else if (tagTypeDO.getName().equals(GenderType.boyTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.girl)) {
                    //获取所有男生话题
                    TagTypeRO tagTypeRO = SocialTagTypeROFactory.getTagTypeRO(tagTypeDO);
                    List<?  extends SocialuniTagDO> TagDOs = tagApi.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.boy);
                    tagTypeRO.setTags(SocialTagROFactory.tagDOToROS(TagDOs));
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
    private List<TagRO> getTagsByTagTypeId(SocialuniTagTypeDO tagTypeDO, String appGenderType) {
        List<?  extends SocialuniTagDO> TagDOs;
        if (appGenderType.equals(GenderType.all)) {
            TagDOs = tagApi.findByTagTypeIdAndStatusOrderByCountDesc(tagTypeDO.getId(), ContentStatus.enable);
        } else {
            TagDOs = tagApi.findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(tagTypeDO.getId(), ContentStatus.enable, appGenderType);
        }
        // 从数据库中获取tag列表
        return SocialTagROFactory.tagDOToROS(TagDOs);
    }
}