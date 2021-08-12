package com.socialuni.sdk.service;

import com.socialuni.sdk.domain.tag.SoicialTagAddDomain;
import com.socialuni.sdk.constant.GenderType;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.store.SocialTagStore;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.model.model.RO.community.tag.TagTypeRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialTagService {
    @Resource
    SocialTagStore socialTagStore;

    @Resource
    SoicialTagAddDomain soicialTagAddDomain;

    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        TagRO tagRO = soicialTagAddDomain.addTag(mineUser, tagAddQO);
        return new ResultRO<>(tagRO);
    }

    public ResultRO<List<TagRO>> queryTags() {
        List<TagRO> tags = socialTagStore.getAllTagsRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagRO>> queryHotTags() {
        List<TagRO> tags = socialTagStore.getHotTags(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        List<TagTypeRO> tags = socialTagStore.getAllTageTypes(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        List<TagTypeRO> tags = socialTagStore.getHotTagTypes();
        return new ResultRO<>(tags);
    }
}