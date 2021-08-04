package com.socialuni.sdk.store;

import com.socialuni.sdk.constant.CommonConst;
import com.socialuni.sdk.constant.GenderType;
import com.socialuni.sdk.constant.status.ContentStatus;
import com.socialuni.sdk.factory.SocialTagROFactory;
import com.socialuni.sdk.factory.SocialTagTypeROFactory;
import com.socialuni.sdk.model.DO.tag.TagDO;
import com.socialuni.sdk.model.DO.tag.TagTypeDO;
import com.socialuni.sdk.repository.TagRepository;
import com.socialuni.sdk.repository.TagTypeRepository;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.model.model.RO.community.tag.TagTypeRO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialTagStore {
    @Resource
    private TagRepository tagRepository;
    @Resource
    private TagTypeRepository tagTypeRepository;

    //获取talk下的
    @Cacheable(cacheNames = "tagIdsByTalkId", key = "#talkId")
    public List<Integer> getTagIdsByTalkId(Integer talkId) {
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

    public List<TagRO> getHotTags(String appGenderType) {
        //为空，或者类型错误
        return this.getHotTagsRedis(appGenderType);
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
        List<TagTypeDO> tagTypes = getTagTypes();
        initTagTypes.addAll(SocialTagTypeROFactory.tagDOToROS(tagTypes));
        return initTagTypes;
    }

    public List<TagTypeRO> getHotTagTypes() {
//        String appGenderType = DevAccountUtils.getAppGenderType();
        String appGenderType = "";
        //为空，或者类型错误
        return this.getHotTagTypesRedis(appGenderType);
    }


    public List<TagTypeRO> getAllTageTypes(String appGenderType) {
        //为空，或者类型错误
        return this.getAllTageTypesRedis(appGenderType);
    }

    @Cacheable(cacheNames = "tagTypesAll", key = "#appGenderType")
    public List<TagTypeRO> getAllTageTypesRedis(String appGenderType) {
        List<TagTypeRO> initTagTypes = new ArrayList<>();
        //插入一个热门类别，根据性别获取一个
        initTagTypes.add(getHotTagType(appGenderType));
        //查询全部，但是不查询子节点
        List<TagTypeDO> tagTypes = getTagTypes();
        initTagTypes.addAll(tagTypesSetTags(tagTypes, appGenderType));
        return initTagTypes;
    }


    //获取热门类型的tagtype
    private TagTypeRO getHotTagType(String appGenderType) {
        //插入一个热门类别
        TagTypeRO hotTagTypeRO = new TagTypeRO();
        hotTagTypeRO.setId(9999);
        hotTagTypeRO.setName("热门");

        hotTagTypeRO.setTags(getHotTags(appGenderType));
        return hotTagTypeRO;
    }


    private List<TagTypeDO> getTagTypes() {
        //查询出来所有启用的类型的tagTypes，按talk数量排序
        return tagTypeRepository.findByStatusAndTalkCountGreaterThanOrderByOrderLevelDescTalkCountDesc(ContentStatus.enable, CommonConst.zero);
    }

    //给tagtype设置它的子标签
    private List<TagTypeRO> tagTypesSetTags(List<TagTypeDO> DOs, String appGenderType) {
        List<TagTypeRO> tagTypeROS = new ArrayList<>();
        for (TagTypeDO tagTypeDO : DOs) {
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
                TagTypeRO tagTypeRO =  SocialTagTypeROFactory.getTagTypeRO(tagTypeDO);
                tagTypeRO.setTags(getTagsByTagTypeId(tagTypeDO, appGenderType));
                tagTypeROS.add(tagTypeRO);
            }
        }
        return tagTypeROS;
    }

    //根据typeid获取所有
    private List<TagRO> getTagsByTagTypeId(TagTypeDO tagTypeDO, String appGenderType) {
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