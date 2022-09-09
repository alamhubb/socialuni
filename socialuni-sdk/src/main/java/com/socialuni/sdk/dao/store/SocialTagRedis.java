package com.socialuni.sdk.dao.store;

import com.socialuni.sdk.logic.factory.community.SocialTagROFactory;
import com.socialuni.sdk.logic.factory.community.SocialTagTypeROFactory;
import com.socialuni.sdk.utils.SocialTagStore;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.dao.DO.tag.TagDO;
import com.socialuni.sdk.dao.DO.tag.SocialuniTagTypeDO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.dao.redis.redisKey.TagRedisKey;
import com.socialuni.sdk.dao.repository.community.TagRepository;
import com.socialuni.sdk.dao.repository.community.SocialuniTagTypeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialTagRedis {
    @Resource
    private TagRepository tagRepository;
    @Resource
    private SocialuniTagTypeRepository tagTypeRepository;
    @Resource
    private SocialTagStore socialTagStore;

    @Cacheable(cacheNames = TagRedisKey.tagById, key = "#tagId")
    public TagDO findTagById(Integer tagId) {
        return tagRepository.findByIdAndStatus(tagId, CommonStatus.enable);
    }

    @Cacheable(cacheNames = TagRedisKey.talkTagsByTalkId, key = "#talkId")
    public List<TagDO> getTagsByTalkId(Integer talkId) {
        List<Integer> tagIds = this.getTagIdsByTalkId(talkId);
        return socialTagStore.findTagsByIds(tagIds);
    }

    //获取talk下的
    private List<Integer> getTagIdsByTalkId(Integer talkId) {
        return tagRepository.findTagIdsByTalkIdAndStatusAndShowFront(talkId, ContentStatus.enable, true);
    }

    /**
     * 查询热门下包含子节点，和所有省份不包含子节点
     *
     * @return
     */
    @Cacheable(cacheNames = "tagsHot", key = "#appGenderType")
    public List<TagRO> getHotTagsRedis(String appGenderType) {
        List<TagDO> tagDOS;
        if (GenderType.all.equals(appGenderType)) {
            tagDOS = tagRepository.findByStatusOrderByCountDesc(ContentStatus.enable, PageRequest.of(0, 10));
        } else {
            tagDOS = tagRepository.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, appGenderType, PageRequest.of(0, 10));
        }
        return SocialTagROFactory.tagDOToROS(tagDOS);
    }

    @Cacheable(cacheNames = "tagsAll", key = "#appGenderType")
    public List<TagRO> getAllTagsRedis(String appGenderType) {
        List<TagDO> tagDOS;
        if (appGenderType.equals(GenderType.all)) {
            tagDOS = tagRepository.findAllByStatusOrderByCountDesc(ContentStatus.enable);
        } else {
            tagDOS = tagRepository.findAllByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, appGenderType);
        }
        return SocialTagROFactory.tagDOToROS(tagDOS);
    }


    @Cacheable(cacheNames = "tagTypesHot", key = "#appGenderType")
    public List<TagTypeRO> getHotTagTypesRedis(String appGenderType) {
        List<TagTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别
        initTagTypes.add(getHotTagType(appGenderType));

        //转成vo
        List<SocialuniTagTypeDO> tagTypes = getTagTypes();
        initTagTypes.addAll(SocialTagTypeROFactory.tagDOToROS(tagTypes));
        return initTagTypes;
    }


    @Cacheable(cacheNames = "tagTypesAll", key = "#appGenderType")
    public List<TagTypeRO> getAllTageTypesRedis(String appGenderType) {
        List<TagTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别，根据性别获取一个
        initTagTypes.add(getHotTagType(appGenderType));
        //查询全部，但是不查询子节点
        List<SocialuniTagTypeDO> tagTypes = getTagTypes();
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


    private List<SocialuniTagTypeDO> getTagTypes() {
        //查询出来所有启用的类型的tagTypes，按talk数量排序
        return tagTypeRepository.findByStatusAndTalkCountGreaterThanOrderByOrderLevelDescTalkCountDesc(ContentStatus.enable, SocialuniConst.zero);
    }

    //给tagtype设置它的子标签
    private List<TagTypeRO> tagTypesSetTags(List<SocialuniTagTypeDO> DOs, String appGenderType) {
        List<TagTypeRO> tagTypeROS = new ArrayList<>();
        for (SocialuniTagTypeDO tagTypeDO : DOs) {
            //如果为女生，且app为男生，则不查询
            if (tagTypeDO.getName().equals(GenderType.girlTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.boy)) {
                    //获取所有女生话题
                    TagTypeRO tagTypeRO = SocialTagTypeROFactory.getTagTypeRO(tagTypeDO);
                    List<TagDO> tagDOS = tagRepository.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.girl);
                    tagTypeRO.setTags(SocialTagROFactory.tagDOToROS(tagDOS));
                    tagTypeROS.add(tagTypeRO);
                }
                //如果为男生专属，查询男生
            } else if (tagTypeDO.getName().equals(GenderType.boyTagTypeName)) {
                //如果tagType和appgender性别相反，什么也不做
                if (!appGenderType.equals(GenderType.girl)) {
                    //获取所有男生话题
                    TagTypeRO tagTypeRO = SocialTagTypeROFactory.getTagTypeRO(tagTypeDO);
                    List<TagDO> tagDOS = tagRepository.findByStatusAndVisibleGenderOrderByCountDesc(ContentStatus.enable, GenderType.boy);
                    tagTypeRO.setTags(SocialTagROFactory.tagDOToROS(tagDOS));
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
        List<TagDO> tagDOS;
        if (appGenderType.equals(GenderType.all)) {
            tagDOS = tagRepository.findByTagTypeIdAndStatusOrderByCountDesc(tagTypeDO.getId(), ContentStatus.enable);
        } else {
            tagDOS = tagRepository.findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(tagTypeDO.getId(), ContentStatus.enable, appGenderType);
        }
        // 从数据库中获取tag列表
        return SocialTagROFactory.tagDOToROS(tagDOS);
    }
}