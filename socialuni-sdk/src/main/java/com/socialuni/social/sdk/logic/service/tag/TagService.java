package com.socialuni.social.sdk.logic.service.tag;

import com.socialuni.social.sdk.constant.TalkOperateType;
import com.socialuni.social.sdk.dao.store.SocialTagRedis;
import com.socialuni.social.sdk.dao.DO.tag.TagDO;
import com.socialuni.social.sdk.dao.DO.tag.SocialuniTagTypeDO;
import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.sdk.dao.repository.community.TagRepository;
import com.socialuni.social.sdk.dao.repository.community.SocialuniTagTypeRepository;
import com.socialuni.social.sdk.utils.SocialTagStore;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author qinkaiyuan
 * @date 2019-11-16 23:09
 */
@Service
public class TagService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TagRepository tagRepository;
    @Resource
    private SocialTagStore tagQueryRepository;
    @Resource
    private SocialuniTagTypeRepository tagTypeRepository;

    @Resource
    private SocialTagRedis socialTagRedis;

    public List<TagDO> checkAndUpdateTagCount(SocialuniUserDO user, List<Integer> tagIds, String talkOperateType) {
        List<TagDO> tagDOList = new ArrayList<>();

//        Integer devId = DevAccountUtils.getDevId();

        if (ObjectUtils.isEmpty(tagIds)) {
            tagIds = new ArrayList<>();
        }

        //list转set去重
        Set<Integer> tagIdsSet = new HashSet<>(tagIds);

        /**
         * 考虑上一版本，可以为空，为空没有影响，toDO 下一版本删除记录用户最近tag的代码，只在用户本地记录
         */
        if (!ObjectUtils.isEmpty(tagIdsSet)) {
            for (Integer tagId : tagIdsSet) {
                if (!ObjectUtils.isEmpty(tagId) && tagId != 0) {
                    //查询启用的话题
                    TagDO tagDO = socialTagRedis.findTagById(tagId);
                    //如果话题存在且可用
                    if (tagDO != null) {
                        if (!ContentStatus.enable.equals(tagDO.getStatus())) {
                            throw new SocialParamsException("引用了不可使用的话题");
                        }

                        //次数加1
                        tagDO.setCount(tagDO.getCount() + 1);
                        if (TalkOperateType.talkAdd.equals(talkOperateType)) {
                            String tagVisibleGender = tagDO.getVisibleGender();
                            //如果为单性话题
                            if (!tagVisibleGender.equals(GenderType.all)) {
                                SocialuniTagTypeDO tagTypeDO = null;
                                if (tagVisibleGender.equals(GenderType.girl)) {
                                    tagTypeDO = tagTypeRepository.findFirstByName(GenderType.girlTagTypeName);
                                } else if (tagVisibleGender.equals(GenderType.boy)) {
                                    tagTypeDO = tagTypeRepository.findFirstByName(GenderType.boyTagTypeName);
                                }
                                //不存在为空的情况
                                tagTypeDO.setTalkCount(tagTypeDO.getTalkCount() + 1);
                                tagTypeRepository.save(tagTypeDO);
                            }
                            tagDO.setTalkCount(tagDO.getTalkCount() + 1);

                            logger.info("tagTypeId:{}", tagDO.getTagTypeId());
                            Optional<SocialuniTagTypeDO> optionalTagTypeDO = tagTypeRepository.findById(tagDO.getTagTypeId());
                            SocialuniTagTypeDO tagTypeDO = optionalTagTypeDO.get();
                            tagTypeDO.setTalkCount(tagTypeDO.getTalkCount() + 1);
                            tagTypeRepository.save(tagTypeDO);
                        }
                        tagDO = tagRepository.save(tagDO);
                        tagDOList.add(tagDO);

                        //暂时不再在后台记录用户最近使用的标签
                    /*if (user != null) {
                        Date curDate = new Date();
                        Optional<UserTagDO> optionalUserTag = userTagRepository.findFirstByUserIdAndTag(user, tagDO);
                        UserTagDO userTagDO;
                        //如果用户存在这个记录
                        if (optionalUserTag.isPresent()) {
                            userTagDO = optionalUserTag.get();
                            if (userTagDO.getStatus().equals(CommonStatus.delete)) {
                                userTagDO.setStatus(CommonStatus.enable);
                            }
                            userTagDO.setCount(userTagDO.getCount() + 1);
                            userTagDO.setUpdateTime(curDate);
                        } else {
                            //给用户new一个
                            userTagDO = new UserTagDO(user, tagDO);
                        }
                        if (TalkOperateType.talkAdd.equals(talkOperateType)) {
                            userTagDO.setTalkCount(userTagDO.getTalkCount() + 1);
                        }
                        userTagRepository.save(userTagDO);
                    }*/
                    } else {
                        /**
                         * 带选择时为null，可以为null，但不能不是null，数据库还不存在
                         */
                        if (!ObjectUtils.isEmpty(tagId)) {
                            //tagId不为null，且数据库中还不存在
                            throw new SocialParamsException("引用了不可使用的话题");
                        }
                    }
                }
            }
        }
        return tagDOList;
    }
}
